package com.mohaberabi.foodiks.core.data.network.client

import com.mohaberabi.foodiks.core.domain.model.ProductModel
import com.mohaberabi.foodiks.core.domain.source.remote.ProductsRemoteDataSource

class StubProductsRemoteDataSource : ProductsRemoteDataSource {
    override suspend fun getAllProducts(): List<ProductModel> {
        return listOf()
    }
}