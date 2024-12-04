package com.mohaberabi.foodiks.core.data.repository

import com.mohaberabi.foodiks.core.domain.model.AppResult
import com.mohaberabi.foodiks.core.domain.model.ProductModel
import com.mohaberabi.foodiks.core.domain.model.handleAppResult
import com.mohaberabi.foodiks.core.domain.model.toFtsModel
import com.mohaberabi.foodiks.core.domain.repository.ProductRepository
import com.mohaberabi.foodiks.core.domain.source.local.ProductFtsLocalDataSource
import com.mohaberabi.foodiks.core.domain.source.local.ProductsLocalDataSource
import com.mohaberabi.foodiks.core.domain.source.remote.ProductsRemoteDataSource
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.mapLatest
import kotlinx.coroutines.joinAll
import kotlinx.coroutines.launch

@OptIn(ExperimentalCoroutinesApi::class)
class OfflineFirstProductRepository(
    private val productFtsLocalDataSource: ProductFtsLocalDataSource,
    private val productLocalDataSource: ProductsLocalDataSource,
    private val productsRemoteDataSource: ProductsRemoteDataSource,
) : ProductRepository {
    override fun searchProducts(
        query: String,
    ): Flow<List<ProductModel>> {
        return productFtsLocalDataSource.searchProducts(query)
            .mapLatest { it.toSet() }
            .distinctUntilChanged()
            .flatMapLatest(productLocalDataSource::getAllProductsByIds)

    }

    override fun getAllProducts(): Flow<List<ProductModel>> =
        productLocalDataSource.getAllProducts()

    override suspend fun sync(): AppResult<Unit> {
        return handleAppResult {
            val remoteProducts = productsRemoteDataSource.getAllProducts()
            val remoteProductsAsFts = remoteProducts.map { it.toFtsModel }
            coroutineScope {
                joinAll(
                    launch { productLocalDataSource.addProducts(remoteProducts) },
                    launch { productFtsLocalDataSource.insertProductsFts(remoteProductsAsFts) },
                )
            }
        }
    }

    override suspend fun refresh(
        forceRemote: Boolean,
    ): AppResult<Unit> {
        val shouldRefresh = productLocalDataSource.isEmpty() || forceRemote
        return if (shouldRefresh) {
            sync()
        } else {
            AppResult.Done(Unit)
        }
    }
}