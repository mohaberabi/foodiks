package com.mohaberabi.foodiks.features.tables.presentation.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mohaberabi.foodiks.core.domain.model.CartItemModel
import com.mohaberabi.foodiks.core.domain.model.CartModel
import com.mohaberabi.foodiks.core.domain.model.onDone
import com.mohaberabi.foodiks.core.domain.model.onError
import com.mohaberabi.foodiks.core.domain.usecase.cart.AddToCartUseCase
import com.mohaberabi.foodiks.core.domain.usecase.cart.GetCartUseCase
import com.mohaberabi.foodiks.core.domain.usecase.categories.GetAllCategoriesUseCase
import com.mohaberabi.foodiks.core.domain.usecase.products.SearchProductsUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class TablesViewModel(
    private val savedStateHandle: SavedStateHandle,
    private val getCart: GetCartUseCase,
    private val searchProducts: SearchProductsUseCase,
    private val getCategories: GetAllCategoriesUseCase,
    private val addToCart: AddToCartUseCase,
) : ViewModel() {
    companion object {
        private const val SEARCH_QUERY_KEY = "searchQueryKey"
        private const val SELECTED_CATEGORY_INDEX = "selectedCategoryIndex"
    }

    private val searchQuery = savedStateHandle.getStateFlow(SEARCH_QUERY_KEY, "")
    private val selectedCategoryIndex = savedStateHandle.getStateFlow(SELECTED_CATEGORY_INDEX, 0)

    @OptIn(ExperimentalCoroutinesApi::class)
    val tablesState: StateFlow<TablesState> = combine(
        searchQuery.flatMapLatest { query -> searchProducts(query) },
        getCategories(),
        selectedCategoryIndex
    ) { products, categories, selectedIndex ->
        TablesState(
            products = products,
            categories = categories,
            selectedCategoryIndex = selectedIndex,
            searchQuery = searchQuery.value,
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

    fun addItemToCart(item: CartItemModel) {
        viewModelScope.launch {
            addToCart(item)
                .onDone { }
                .onError { }
        }
    }

    fun searchQueryChanged(value: String) {
        savedStateHandle[SEARCH_QUERY_KEY] = value
    }


    fun categoryIndexChanged(index: Int) {
        savedStateHandle[SELECTED_CATEGORY_INDEX] = index
    }

}