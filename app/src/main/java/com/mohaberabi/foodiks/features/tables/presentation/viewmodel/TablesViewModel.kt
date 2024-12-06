package com.mohaberabi.foodiks.features.tables.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mohaberabi.foodiks.core.common.util.extension.asUiText
import com.mohaberabi.foodiks.core.common.util.retryExponentBackOff
import com.mohaberabi.foodiks.core.domain.model.AppResult
import com.mohaberabi.foodiks.core.domain.model.CartItemModel
import com.mohaberabi.foodiks.core.domain.model.CartModel
import com.mohaberabi.foodiks.core.domain.model.ProductModel
import com.mohaberabi.foodiks.core.domain.model.onDone
import com.mohaberabi.foodiks.core.domain.model.onError
import com.mohaberabi.foodiks.core.domain.model.toCartModel
import com.mohaberabi.foodiks.core.domain.usecase.cart.AddToCartUseCase
import com.mohaberabi.foodiks.core.domain.usecase.cart.ClearCartUseCase
import com.mohaberabi.foodiks.core.domain.usecase.cart.GetCartUseCase
import com.mohaberabi.foodiks.core.domain.usecase.categories.GetAllCategoriesUseCase
import com.mohaberabi.foodiks.core.domain.usecase.categories.RefreshCategoriesUseCase
import com.mohaberabi.foodiks.core.domain.usecase.products.RefreshProductsUseCase
import com.mohaberabi.foodiks.core.domain.usecase.products.SearchProductsUseCase
import com.mohaberabi.foodiks.core.domain.usecase.sync.CheckSyncingUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.joinAll
import kotlinx.coroutines.launch

@OptIn(ExperimentalCoroutinesApi::class)
class TablesViewModel(
    getCart: GetCartUseCase,
    getCategories: GetAllCategoriesUseCase,
    checkSyncing: CheckSyncingUseCase,
    private val savedStateHandle: SavedStateHandle,
    private val searchProducts: SearchProductsUseCase,
    private val addToCart: AddToCartUseCase,
    private val clearCart: ClearCartUseCase,
    private val refreshCategories: RefreshCategoriesUseCase,
    private val refreshProducts: RefreshProductsUseCase,
) : ViewModel() {
    companion object {
        private const val SEARCH_QUERY_KEY = "searchQueryKey"
        private const val SELECTED_CATEGORY_INDEX = "selectedCategoryIndex"
    }

    private val flowRestarter = MutableStateFlow<Any>(Unit)
    private val _isRefreshing = MutableStateFlow(false)
    val isRefreshing = _isRefreshing.asStateFlow()
    val searchQuery = savedStateHandle.getStateFlow(SEARCH_QUERY_KEY, "")
    val selectedCategoryIndex = savedStateHandle.getStateFlow(SELECTED_CATEGORY_INDEX, 0)


    private val _events = Channel<TablesEvents>()
    val events = _events.receiveAsFlow()


    @OptIn(FlowPreview::class)
    val tablesState: StateFlow<TablesState> = flowRestarter.flatMapLatest {
        combine(
            searchQuery.debounce(200).flatMapLatest { query -> searchProducts(query) },
            getCategories(),
        ) { products, categories ->
            TablesState(
                products = products,
                categories = categories,
                status = TablesStatus.Populated
            )
        }.retryExponentBackOff(
            whileAttmpting = {
                emit(TablesState(status = TablesStatus.Loading))
            },
        ).catch {
            emit(TablesState(status = TablesStatus.Error))
        }
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = TablesState(status = TablesStatus.Loading)
    )


    val cartState = getCart()
        .retryExponentBackOff()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.Eagerly,
            initialValue = CartModel()
        )

    val syncingState = checkSyncing()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.Eagerly,
            initialValue = false,
        )

    fun addItemToCart(item: ProductModel) {
        viewModelScope.launch {
            addToCart(item.toCartModel)
                .onError { _events.send(TablesEvents.Error(it.error.asUiText())) }
        }
    }

    fun confirmOrder() {
        viewModelScope.launch {
            clearCart()
                .onDone { _events.send(TablesEvents.OrderDone) }
                .onError { _events.send(TablesEvents.Error(it.error.asUiText())) }
        }
    }

    fun searchQueryChanged(value: String) {
        savedStateHandle[SEARCH_QUERY_KEY] = value
    }


    fun categoryIndexChanged(index: Int) {
        savedStateHandle[SELECTED_CATEGORY_INDEX] = index
    }


    fun retryGettingData() {
        flowRestarter.update { Any() }
    }

    fun refreshData() {
        viewModelScope.launch {
            _isRefreshing.update { true }
            joinAll(
                launch { refreshCategories(forceRemote = true) },
                launch { refreshProducts(forceRemote = true) }
            )
            _isRefreshing.update { false }
        }
    }

}