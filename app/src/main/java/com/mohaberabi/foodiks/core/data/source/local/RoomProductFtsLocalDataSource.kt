package com.mohaberabi.foodiks.core.data.source.local

import com.mohaberabi.foodiks.core.data.database.dao.ProductFtsDao
import com.mohaberabi.foodiks.core.data.database.mapper.toProductFtsEntity
import com.mohaberabi.foodiks.core.domain.model.ProductFtsModel
import com.mohaberabi.foodiks.core.domain.source.local.ProductFtsLocalDataSource
import com.mohaberabi.foodiks.core.domain.source.local.ProductId
import com.mohaberabi.foodiks.core.common.util.DispatchersProvider
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.withContext

class RoomProductFtsLocalDataSource(
    private val dispatchers: DispatchersProvider,
    private val dao: ProductFtsDao
) : ProductFtsLocalDataSource {
    override suspend fun insertProductsFts(
        items: List<ProductFtsModel>,
    ) {
        withContext(dispatchers.io) {
            dao.insertFtsProducts(items.map { it.toProductFtsEntity })
        }
    }

    override fun searchProducts(
        query: String,
    ): Flow<List<ProductId>> = dao.searchProducts(query).flowOn(dispatchers.io)
}