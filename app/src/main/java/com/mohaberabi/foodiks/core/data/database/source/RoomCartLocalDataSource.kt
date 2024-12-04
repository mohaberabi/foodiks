package com.mohaberabi.foodiks.core.data.database.source

import com.mohaberabi.foodiks.core.data.database.dao.CartDao
import com.mohaberabi.foodiks.core.data.database.mapper.toCartItemEntity
import com.mohaberabi.foodiks.core.data.database.mapper.toCartItemModel
import com.mohaberabi.foodiks.core.domain.model.CartItemModel
import com.mohaberabi.foodiks.core.domain.source.local.CartItemMap
import com.mohaberabi.foodiks.core.domain.source.local.CartLocalDataSource
import com.mohaberabi.foodiks.core.common.util.DispatchersProvider
import com.mohaberabi.foodiks.core.data.database.ext.executeDatabaseOperation
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext

class RoomCartLocalDataSource(
    private val dispatchers: DispatchersProvider,
    private val cartDao: CartDao,
) : CartLocalDataSource {
    override fun getCartItems(
    ): Flow<CartItemMap> = cartDao.getCartItems().map { list ->
        list.associate { it.id to it.toCartItemModel }
    }.flowOn(dispatchers.io)

    override suspend fun addToCart(item: CartItemModel) {
        withContext(dispatchers.io) {
            executeDatabaseOperation {
                cartDao.addToCart(item.toCartItemEntity)
            }
        }
    }

    override suspend fun clearCart() {
        withContext(dispatchers.io) {
            executeDatabaseOperation {
                cartDao.clearCart()
            }
        }
    }
}