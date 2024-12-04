package com.mohaberabi.foodiks.core.domain.source.local

import com.mohaberabi.foodiks.core.domain.model.ProductModel
import kotlinx.coroutines.flow.Flow


interface ProductsLocalDataSource {
    suspend fun addProducts(
        products: List<ProductModel>,
    )

    suspend fun isEmpty(): Boolean

    fun getAllProducts(): Flow<List<ProductModel>>
    fun getAllProductsByIds(
        ids: Set<String>,
    ): Flow<List<ProductModel>>

}