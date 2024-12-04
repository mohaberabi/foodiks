package com.mohaberabi.foodiks.features.tables.presentation.viewmodel

import androidx.compose.runtime.Stable
import com.mohaberabi.foodiks.core.domain.model.CategoryModel
import com.mohaberabi.foodiks.core.domain.model.ProductModel


enum class TablesStatus {
    Initial,
    Loading,
    Populated,
    Error;

    val isLoading: Boolean get() = this == Loading
}

@Stable
data class TablesState(
    val products: List<ProductModel> = listOf(),
    val categories: List<CategoryModel> = listOf(),
    val status: TablesStatus = TablesStatus.Initial
)
