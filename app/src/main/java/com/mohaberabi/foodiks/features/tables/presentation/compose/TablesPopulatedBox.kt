package com.mohaberabi.foodiks.features.tables.presentation.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.mohaberabi.foodiks.core.domain.model.CartModel
import com.mohaberabi.foodiks.core.domain.model.ProductModel
import com.mohaberabi.foodiks.core.presentation.compose.CartButton
import com.mohaberabi.foodiks.core.presentation.design_system.theme.Spacing
import com.mohaberabi.foodiks.core.presentation.extensions.clearFocusOnTap
import com.mohaberabi.foodiks.features.tables.presentation.viewmodel.TablesState
import com.mohaberabi.foodiks.features.tables.presentation.viewmodel.TablesStatus
import com.mohaberabi.jetmart.core.presentation.compose.AppLoader


@Composable
fun TablesPopualtedBox(
    modifier: Modifier = Modifier,
    cartState: CartModel,
    tablesState: TablesState,
    selectedIndex: Int,
    onSearch: (String) -> Unit,
    onCategoryClicked: (Int) -> Unit = {},
    onProductClick: (ProductModel) -> Unit,
    searchQuery: String,
    onConfirmOrder: () -> Unit = {},
    rowScrollState: LazyListState = rememberLazyListState(),
    gridScrollState: LazyGridState = rememberLazyGridState()
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .clearFocusOnTap(),
        contentAlignment = Alignment.BottomCenter,
    ) {
        Column(
        ) {
            MenuStatusTopBar()
            SearchTextField(
                onTextChanged = onSearch,
                value = searchQuery,
            )
            CategoryLazyRow(
                categories = tablesState.categories,
                scrollState = rowScrollState,
                selectedCategoryIndex = selectedIndex,
                onCategoryClick = onCategoryClicked
            )

            ResponsiveProductGrid(
                modifier = Modifier
                    .padding(bottom = 60.dp)
                    .background(Color.LightGray.copy(alpha = 0.33f)),
                scrollState = gridScrollState,
                products = tablesState.products,
                cartQty = { cartState[it.id]?.qty ?: 0 },
                onProductClick = onProductClick
            )
        }
        CartButton(
            onClick = onConfirmOrder,
            height = 60.dp,
            enabled = cartState.isNotEmpty(),
            cartTotal = cartState.cartTotalFormatted,
            cartSize = cartState.cartSizeFormatted
        )
    }
}