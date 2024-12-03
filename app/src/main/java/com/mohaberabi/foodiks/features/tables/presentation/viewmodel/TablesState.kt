package com.mohaberabi.foodiks.features.tables.presentation.viewmodel

import androidx.compose.runtime.Stable
import com.mohaberabi.foodiks.core.domain.model.CategoryModel
import com.mohaberabi.foodiks.core.domain.model.ProductModel


enum class TablesStatus {
    Initial,
    Loading,
    Populated,
    Error,
}

@Stable
data class TablesState(
    val products: List<ProductModel> = listOf(),
    val categories: List<CategoryModel> = listOf(),
    val selectedCategoryIndex: Int = 0,
    val searchQuery: String = "",
    val status: TablesStatus = TablesStatus.Initial
)
