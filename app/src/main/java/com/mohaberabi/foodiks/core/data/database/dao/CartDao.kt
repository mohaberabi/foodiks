package com.mohaberabi.foodiks.core.data.database.dao

import androidx.room.*
import com.mohaberabi.foodiks.core.data.database.entity.CartEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface CartDao {

    @Query("SELECT * FROM cart")
    fun getCartItems(): Flow<List<CartEntity>>

    @Query("DELETE FROM cart")
    suspend fun clearCart()

    @Query("SELECT qty FROM cart WHERE id = :id")
    suspend fun getCartItemQuantity(id: String): Int?

    @Upsert
    suspend fun insertCartItem(cartItem: CartEntity)

    @Query("UPDATE cart SET qty = qty + :quantity WHERE id = :id")
    suspend fun updateCartItemQuantity(id: String, quantity: Int)

    @Transaction
    suspend fun addToCart(cartItem: CartEntity) {
        val currentQuantity = getCartItemQuantity(cartItem.id)
        if (currentQuantity == null) {
            insertCartItem(cartItem.copy(qty = 1))
        } else {
            updateCartItemQuantity(cartItem.id, cartItem.qty)
        }
    }
}