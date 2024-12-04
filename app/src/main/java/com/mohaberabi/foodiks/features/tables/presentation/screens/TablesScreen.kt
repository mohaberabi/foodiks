package com.mohaberabi.foodiks.features.tables.presentation.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.mohaberabi.foodiks.core.domain.model.CartModel
import com.mohaberabi.foodiks.core.domain.model.CategoryModel
import com.mohaberabi.foodiks.core.domain.model.ProductModel
import com.mohaberabi.foodiks.core.presentation.compose.AppPlaceHolder
import com.mohaberabi.foodiks.core.presentation.compose.EventCollector
import com.mohaberabi.foodiks.core.presentation.design_system.theme.FoodiksTheme
import com.mohaberabi.foodiks.core.presentation.extensions.clearFocusOnTap
import com.mohaberabi.foodiks.features.tables.presentation.compose.TablesPopualtedBox
import com.mohaberabi.foodiks.features.tables.presentation.viewmodel.TablesEvents
import com.mohaberabi.foodiks.features.tables.presentation.viewmodel.TablesState
import com.mohaberabi.foodiks.features.tables.presentation.viewmodel.TablesViewModel
import com.mohaberabi.foodiks.foodiks.LocalSnackBarController
import com.mohaberabi.jetmart.core.presentation.compose.AppLoader
import org.koin.androidx.compose.koinViewModel


@Composable
fun TablesScreenRoot(
    viewmodel: TablesViewModel = koinViewModel()
) {
    val snackBarController = LocalSnackBarController.current
    val tablesState by viewmodel.tablesState.collectAsStateWithLifecycle()
    val cartState by viewmodel.cartState.collectAsStateWithLifecycle()
    val searchQuery by viewmodel.searchQuery.collectAsStateWithLifecycle()
    val syncing by viewmodel.syncingState.collectAsStateWithLifecycle()
    val selectedCategoryIndex by viewmodel.selectedCategoryIndex.collectAsStateWithLifecycle()

    EventCollector(
        flow = viewmodel.events,
    ) { event ->
        when (event) {
            TablesEvents.OrderDone -> snackBarController.show("Order Made")
            is TablesEvents.Error -> snackBarController.show(event.error)

        }
    }
    TablesScreen(
        cartState = cartState,
        tablesState = tablesState,
        onSearch = viewmodel::searchQueryChanged,
        onCategoryClicked = viewmodel::categoryIndexChanged,
        onProductClick = viewmodel::addItemToCart,
        searchQuery = searchQuery,
        onConfirmOrder = viewmodel::confirmOrder,
        isSyncing = syncing,
        selectedCategoryIndex = selectedCategoryIndex,
        onRefresh = viewmodel::refreshData
    )
}


@Composable
fun TablesScreen(
    modifier: Modifier = Modifier,
    cartState: CartModel,
    tablesState: TablesState,
    onSearch: (String) -> Unit,
    onCategoryClicked: (Int) -> Unit = {},
    onProductClick: (ProductModel) -> Unit,
    onConfirmOrder: () -> Unit = {},
    onRefresh: () -> Unit = {},
    searchQuery: String,
    isSyncing: Boolean,
    selectedCategoryIndex: Int,
) {
    var categoryToProductIndexMap by remember { mutableStateOf<Map<String, Int>>(mapOf()) }
    LaunchedEffect(tablesState.products) {
        if (categoryToProductIndexMap.size != tablesState.products.size) {
            categoryToProductIndexMap = tablesState.products
                .mapIndexed { index, product -> product.category.id to index }
                .groupBy({ it.first }, { it.second })
                .mapValues { it.value.first() }
        }
    }

    val rowScrollState = rememberLazyListState()
    val gridScrollState = rememberLazyGridState()
    LaunchedEffect(
        key1 = selectedCategoryIndex
    ) {
        rowScrollState.animateScrollToItem(selectedCategoryIndex)
        if (tablesState.products.isNotEmpty()) {
            val currentCategory = tablesState.categories[selectedCategoryIndex]
            val index = categoryToProductIndexMap[currentCategory.id]
            index?.let {
                gridScrollState.animateScrollToItem(it)
            }
        }

    }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .clearFocusOnTap(),
        contentAlignment = Alignment.BottomCenter,
    ) {


        if (isSyncing || tablesState.status.isLoading) {
            AppLoader()
        } else {
            TablesPopualtedBox(
                modifier = modifier,
                onCategoryClicked = onCategoryClicked,
                onConfirmOrder = onConfirmOrder,
                rowScrollState = rowScrollState,
                gridScrollState = gridScrollState,
                cartState = cartState,
                tablesState = tablesState,
                onSearch = onSearch,
                onProductClick = onProductClick,
                searchQuery = searchQuery,
                selectedIndex = selectedCategoryIndex
            )
        }
        PlaceHolder(
            query = searchQuery,
            products = tablesState.products,
            categories = tablesState.categories,
            onRefresh = onRefresh,
            loading = isSyncing || tablesState.status.isLoading
        )

    }

}

@Composable
private fun BoxScope.PlaceHolder(
    query: String,
    products: List<*>,
    categories: List<*>,
    onRefresh: () -> Unit,
    loading: Boolean
) {
    if (!loading) {
        when {
            products.isEmpty() && categories.isEmpty() -> AppPlaceHolder(
                onRetry = onRefresh,
            )

            query.isNotEmpty() && products.isEmpty() -> AppPlaceHolder()

            else -> Unit
        }
    }

}

@Preview(
    showBackground = true,
)
@Composable
private fun PreviewTablesScreen() {

    FoodiksTheme {
        TablesScreen(
            selectedCategoryIndex = 0,
            cartState = CartModel(),
            onCategoryClicked = {},
            onSearch = {},
            onProductClick = {},
            isSyncing = false,
            searchQuery = "",
            tablesState = TablesState(
                products = listOf(
                    ProductModel("1", "Tilabia fish", 200.0, CategoryModel("", ""), "", ""),
                    ProductModel("1", "Tilabia fish", 200.0, CategoryModel("", ""), "", ""),
                    ProductModel("1", "Tilabia fish", 200.0, CategoryModel("", ""), "", "")
                )
            )
        )
    }
}