package com.mohaberabi.foodiks.core.data.repository

import com.mohaberabi.foodiks.core.domain.model.AppResult
import com.mohaberabi.foodiks.core.domain.model.CartItemModel
import com.mohaberabi.foodiks.core.domain.model.CartModel
import com.mohaberabi.foodiks.core.domain.model.handleAppResult
import com.mohaberabi.foodiks.core.domain.repository.CartRepository
import com.mohaberabi.foodiks.core.domain.source.local.CartLocalDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class OfflineFirstCartRepository(
    private val cartLocalDataSource: CartLocalDataSource,
) : CartRepository {
    override fun getCartItems(): Flow<CartModel> =
        cartLocalDataSource.getCartItems().map { CartModel(it) }

    override suspend fun addToCart(
        item: CartItemModel,
    ): AppResult<Unit> = handleAppResult { cartLocalDataSource.addToCart(item) }

    override suspend fun clearCart(): AppResult<Unit> =
        handleAppResult { cartLocalDataSource.clearCart() }
}