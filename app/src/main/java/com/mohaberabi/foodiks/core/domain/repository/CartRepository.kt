package com.mohaberabi.foodiks.core.domain.repository

import com.mohaberabi.foodiks.core.domain.model.AppResult
import com.mohaberabi.foodiks.core.domain.model.CartItemModel
import com.mohaberabi.foodiks.core.domain.model.CartModel
import com.mohaberabi.foodiks.core.domain.source.local.CartItemMap
import kotlinx.coroutines.flow.Flow

interface CartRepository {
    fun getCartItems(): Flow<CartModel>
    suspend fun addToCart(item: CartItemModel): AppResult<Unit>
    suspend fun clearCart(): AppResult<Unit>
}