package com.mohaberabi.foodiks.features.tables.presentation.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.mohaberabi.foodiks.R
import com.mohaberabi.foodiks.core.domain.model.CartModel
import com.mohaberabi.foodiks.core.domain.model.CategoryModel
import com.mohaberabi.foodiks.core.domain.model.ProductModel
import com.mohaberabi.foodiks.core.presentation.compose.AppPlaceHolder
import com.mohaberabi.foodiks.core.presentation.compose.EventCollector
import com.mohaberabi.foodiks.core.presentation.design_system.theme.FoodiksTheme
import com.mohaberabi.foodiks.core.presentation.extensions.clearFocusOnTap
import com.mohaberabi.foodiks.features.tables.presentation.compose.CategoryLazyRow
import com.mohaberabi.foodiks.features.tables.presentation.compose.MenuStatusTopBar
import com.mohaberabi.foodiks.features.tables.presentation.compose.SearchTextField
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
    val context = LocalContext.current
    val snackBarController = LocalSnackBarController.current
    val tablesState by viewmodel.tablesState.collectAsStateWithLifecycle()
    val cartState by viewmodel.cartState.collectAsStateWithLifecycle()
    val searchQuery by viewmodel.searchQuery.collectAsStateWithLifecycle()
    val syncing by viewmodel.syncingState.collectAsStateWithLifecycle()
    val isRefreshing by viewmodel.isRefreshing.collectAsStateWithLifecycle()
    val selectedCategoryIndex by viewmodel.selectedCategoryIndex.collectAsStateWithLifecycle()
    // products must carry a mapping of their   corresponding category ids
    // so that we can apply the animated scrolling combined with the products and category
    // so it is cached inside of a remember block as a preprocessing step
    // eit can be even optimized further by storing the category index inside of the product data structure so
    // we can achieve it at constant time without even preprocessing
    val categoryToProductIndexMap by remember(tablesState.products) {
        derivedStateOf {
            tablesState.products
                .mapIndexed { index, product -> product.category.id to index }
                .groupBy({ it.first }, { it.second })
                .mapValues { it.value.first() }
        }
    }
    EventCollector(
        flow = viewmodel.events,
    ) { event ->
        when (event) {
            TablesEvents.OrderDone -> snackBarController.show(context.getString(R.string.order_made))
            is TablesEvents.Error -> snackBarController.show(event.error.fold(context))

        }
    }
    TablesScreen(
        isRefreshing = isRefreshing,
        cartState = cartState,
        tablesState = tablesState,
        onSearch = viewmodel::searchQueryChanged,
        onCategoryClicked = viewmodel::categoryIndexChanged,
        onProductClick = viewmodel::addItemToCart,
        searchQuery = searchQuery,
        onConfirmOrder = viewmodel::confirmOrder,
        isSyncing = syncing,
        selectedCategoryIndex = selectedCategoryIndex,
        onRefresh = viewmodel::refreshData,
        categoryToProductIndexMap = categoryToProductIndexMap,
        onRetry = viewmodel::retryGettingData,
    )
}


@Composable
fun TablesScreen(
    modifier: Modifier = Modifier,
    cartState: CartModel,
    tablesState: TablesState,
    onSearch: (String) -> Unit = {},
    onCategoryClicked: (Int) -> Unit = {},
    onProductClick: (ProductModel) -> Unit = {},
    onConfirmOrder: () -> Unit = {},
    onRefresh: () -> Unit = {},
    onRetry :()->Unit = {},
    searchQuery: String,
    isSyncing: Boolean,
    isRefreshing:Boolean = false,
    selectedCategoryIndex: Int,
    categoryToProductIndexMap: Map<String, Int> = mapOf()
) {
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
    Column(
        modifier = modifier
            .fillMaxSize()
            .clearFocusOnTap(),
    ) {
        MenuStatusTopBar()
        SearchTextField(
            onTextChanged = onSearch,
            value = searchQuery,
        )
        when {
            tablesState.status.isError -> AppPlaceHolder(
                title = stringResource(R.string.something_went_wrong),
                onRetry = onRetry
            )

           tablesState.status.isLoading -> AppLoader()
            else -> {
                TablesPopualtedBox(
                    modifier = modifier,
                    onConfirmOrder = onConfirmOrder,
                    gridScrollState = gridScrollState,
                    cartState = cartState,
                    products = tablesState.products,
                    categories = tablesState.categories,
                    selectedCategoryIndex = selectedCategoryIndex,
                    searchQuery = searchQuery,
                    onCategoryClicked = onCategoryClicked,
                    onProductClick = onProductClick,
                    rowScrollState = rowScrollState,
                    onRefreshData = onRefresh,
                    isRefreshingData = isRefreshing||isSyncing
                )
            }

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