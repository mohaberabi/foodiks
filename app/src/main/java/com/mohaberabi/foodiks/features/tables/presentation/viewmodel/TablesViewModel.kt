package com.mohaberabi.foodiks.features.tables.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

@OptIn(ExperimentalCoroutinesApi::class)
class TablesViewModel(
    getCart: GetCartUseCase,
    getCategories: GetAllCategoriesUseCase,
    private val savedStateHandle: SavedStateHandle,
    private val searchProducts: SearchProductsUseCase,
    private val addToCart: AddToCartUseCase,
    private val clearCart: ClearCartUseCase,
    private val refreshCategories: RefreshCategoriesUseCase,
    private val refreshProducts: RefreshProductsUseCase,
    private val checkSyncing: CheckSyncingUseCase,
) : ViewModel() {
    companion object {
        private const val SEARCH_QUERY_KEY = "searchQueryKey"
        private const val SELECTED_CATEGORY_INDEX = "selectedCategoryIndex"
    }

    val searchQuery = savedStateHandle.getStateFlow(SEARCH_QUERY_KEY, "")
    val selectedCategoryIndex = savedStateHandle.getStateFlow(SELECTED_CATEGORY_INDEX, 0)


    private val _events = Channel<TablesEvents>()
    val events = _events.receiveAsFlow()


    val tablesState: StateFlow<TablesState> = combine(
        searchQuery.flatMapLatest { query -> searchProducts(query) },
        getCategories(),
    ) { products, categories ->
        TablesState(
            products = products,
            categories = categories,
            status = TablesStatus.Populated
        )
    }.catch {
        emit(TablesState(status = TablesStatus.Error))
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = TablesState(status = TablesStatus.Loading)
    )


    val cartState = getCart().stateIn(
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
                .onDone { }
                .onError { }
        }
    }

    fun confirmOrder() {
        viewModelScope.launch {
            clearCart().onDone { _events.send(TablesEvents.OrderDone) }
                .onError { }
        }
    }

    fun searchQueryChanged(value: String) {
        savedStateHandle[SEARCH_QUERY_KEY] = value
    }


    fun categoryIndexChanged(index: Int) {
        savedStateHandle[SELECTED_CATEGORY_INDEX] = index
    }

    fun refreshData() {
        viewModelScope.launch {
            awaitAll(
                async { refreshCategories(forceRemote = true) },
                async { refreshProducts(forceRemote = true) }
            ).forEach { res ->
                if (res is AppResult.Error) {
                    _events.send(TablesEvents.Error(res.error.toString()))
                    return@launch
                }
            }
        }
    }

}