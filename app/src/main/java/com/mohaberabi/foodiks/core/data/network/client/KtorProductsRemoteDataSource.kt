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

private val stubImages = listOf(
    "https://images.pexels.com/photos/1640777/pexels-photo-1640777.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1",
    "https://images.pexels.com/photos/376464/pexels-photo-376464.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1",
    "https://images.pexels.com/photos/70497/pexels-photo-70497.jpeg",
    "https://images.pexels.com/photos/1279330/pexels-photo-1279330.jpeg?auto=compress&cs=tinysrgb&w=600"
)

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
                response.map { it.toProductModel().copy(image = stubImages.random()) }
            }
        }
    }
}