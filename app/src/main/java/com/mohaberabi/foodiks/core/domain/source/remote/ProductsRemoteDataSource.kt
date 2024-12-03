package com.mohaberabi.foodiks.core.domain.source.remote

import com.mohaberabi.foodiks.core.domain.model.ProductModel

interface ProductsRemoteDataSource {
    suspend fun getAllProducts(): List<ProductModel>
}