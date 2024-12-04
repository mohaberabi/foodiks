package com.mohaberabi.foodiks.core.data.database.source

import com.mohaberabi.foodiks.core.data.database.dao.ProductsDao
import com.mohaberabi.foodiks.core.data.database.mapper.toProductEntity
import com.mohaberabi.foodiks.core.data.database.mapper.toProductModel
import com.mohaberabi.foodiks.core.domain.model.ProductModel
import com.mohaberabi.foodiks.core.domain.source.local.ProductsLocalDataSource
import com.mohaberabi.foodiks.core.common.util.DispatchersProvider
import com.mohaberabi.foodiks.core.data.database.ext.executeDatabaseOperation
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext

class RoomProductsLocalDataSource(
    private val productsDao: ProductsDao,
    private val dispatchers: DispatchersProvider,
) : ProductsLocalDataSource {
    override suspend fun addProducts(products: List<ProductModel>) {
        withContext(dispatchers.io) {
            executeDatabaseOperation {
                productsDao.addProducts(products.map { it.toProductEntity })

            }
        }
    }

    override suspend fun isEmpty(): Boolean {
        return withContext(dispatchers.io) {
            executeDatabaseOperation {
                productsDao.isEmpty()
            }
        }
    }

    override fun getAllProducts(): Flow<List<ProductModel>> =
        productsDao.getAllProducts().map { list -> list.map { it.toProductModel } }
            .flowOn(dispatchers.io)

    override fun getAllProductsByIds(ids: Set<String>): Flow<List<ProductModel>> =
        productsDao.getAllProductsByIds(ids).map { list -> list.map { it.toProductModel } }
            .flowOn(dispatchers.io)

}