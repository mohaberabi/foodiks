package com.mohaberabi.foodiks.features.tables.presentation.compose

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.mohaberabi.foodiks.core.domain.model.CategoryModel


@Composable
fun CategoryLazyRow(
    modifier: Modifier = Modifier,
    scrollState: LazyListState = rememberLazyListState(),
    categories: List<CategoryModel> = listOf(),
    selectedCategoryIndex: Int = 0,
    onCategoryClick: (Int) -> Unit
) {
    LazyRow(
        state = scrollState,
        modifier = modifier.fillMaxWidth(),
    ) {
        items(categories.size) { index ->
            val category = categories[index]
            CategoryTab(
                onClick = { onCategoryClick(index) },
                name = category.name,
                selected = index == selectedCategoryIndex
            )
        }
    }
}