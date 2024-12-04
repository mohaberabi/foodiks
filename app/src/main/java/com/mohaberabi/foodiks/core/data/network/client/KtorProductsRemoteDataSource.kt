package com.mohaberabi.foodiks.core.data.network.client

import com.mohaberabi.foodiks.core.common.util.DispatchersProvider
import com.mohaberabi.foodiks.core.common.util.executeWithRetry
import com.mohaberabi.foodiks.core.data.network.NetworkConst
import com.mohaberabi.foodiks.core.data.network.dto.ProductDto
import com.mohaberabi.foodiks.core.data.network.ext.safeRemoteCall
import com.mohaberabi.foodiks.core.data.network.mapper.toProductModel
import com.mohaberabi.foodiks.core.domain.model.ProductModel
import com.mohaberabi.foodiks.core.domain.source.remote.ProductsRemoteDataSource
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import kotlinx.coroutines.withContext

class KtorProductsRemoteDataSource(
    private val client: HttpClient,
    private val dispatchers: DispatchersProvider
) : ProductsRemoteDataSource {
    override suspend fun getAllProducts(): List<ProductModel> {
        return withContext(dispatchers.io) {
            executeWithRetry {
                val response = safeRemoteCall<List<ProductDto>> {
                    client.get(urlString = NetworkConst.PRODUCT_END_POINT)
                }
                response.map { it.toProductModel() }
            }
        }
    }
}