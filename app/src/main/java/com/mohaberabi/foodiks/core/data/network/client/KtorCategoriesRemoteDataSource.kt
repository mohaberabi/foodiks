package com.mohaberabi.foodiks.core.data.network.client

import com.mohaberabi.foodiks.core.common.util.DispatchersProvider
import com.mohaberabi.foodiks.core.common.util.executeWithRetry
import com.mohaberabi.foodiks.core.data.network.NetworkConst
import com.mohaberabi.foodiks.core.data.network.dto.CategoryDto
import com.mohaberabi.foodiks.core.data.network.ext.safeRemoteCall
import com.mohaberabi.foodiks.core.data.network.mapper.toCategoryModel
import com.mohaberabi.foodiks.core.domain.model.CategoryModel
import com.mohaberabi.foodiks.core.domain.source.remote.CategoryRemoteDataSource
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import kotlinx.coroutines.withContext

class KtorCategoriesRemoteDataSource(
    private val client: HttpClient,
    private val dispatchers: DispatchersProvider
) : CategoryRemoteDataSource {
    override suspend fun getAllCategories(): List<CategoryModel> {
        return withContext(dispatchers.io) {
            executeWithRetry {
                val response = safeRemoteCall<List<CategoryDto>> {
                    client.get(urlString = NetworkConst.CATEGORY_END_POINT)
                }
                response.map { it.toCategoryModel() }
            }
        }

    }
}