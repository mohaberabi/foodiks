package com.mohaberabi.foodiks.features.tables.presentation.compose


import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import com.mohaberabi.foodiks.core.domain.model.ProductModel

@Composable
fun ResponsiveProductGrid(
    products: List<ProductModel>,
    modifier: Modifier = Modifier,
    scrollState: LazyGridState = rememberLazyGridState(),
    onProductClick: (ProductModel) -> Unit,
    cartQty: (ProductModel) -> Int,
) {
    val configuration = LocalConfiguration.current
    val screenWidthDp = configuration.screenWidthDp

    val columns = when {
        screenWidthDp < 600 -> 2
        screenWidthDp < 840 -> 3
        else -> 4
    }

    LazyVerticalGrid(
        state = scrollState,
        columns = GridCells.Fixed(columns),
        modifier = modifier,
    ) {
        items(products) { product ->
            ProductCard(
                onClick = { onProductClick(product) },
                product = product,
                cartQty = cartQty(product)
            )
        }
    }
}