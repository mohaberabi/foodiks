package com.mohaberabi.foodiks.core.domain.source.local

import com.mohaberabi.foodiks.core.domain.model.ProductFtsModel
import com.mohaberabi.foodiks.core.domain.model.ProductModel
import kotlinx.coroutines.flow.Flow

typealias ProductId = String

interface ProductFtsLocalDataSource {
    suspend fun insertProductsFts(
        items: List<ProductFtsModel>,
    )

 
    fun searchProducts(
        query: String,
    ): Flow<List<ProductId>>
}