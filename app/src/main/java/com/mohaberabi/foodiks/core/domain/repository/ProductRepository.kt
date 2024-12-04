package com.mohaberabi.foodiks.core.domain.repository

import com.mohaberabi.foodiks.core.domain.model.ProductModel
import com.mohaberabi.foodiks.core.common.Refreshable
import com.mohaberabi.foodiks.core.common.Syncable
import kotlinx.coroutines.flow.Flow

interface ProductRepository : Syncable, Refreshable {

    fun searchProducts(
        query: String
    ): Flow<List<ProductModel>>

    fun getAllProducts(): Flow<List<ProductModel>>


}