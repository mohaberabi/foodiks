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

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCartItem(cartItem: CartEntity)
    
    @Transaction
    suspend fun addToCart(cartItem: CartEntity) {
        val currentQuantity = getCartItemQuantity(cartItem.id)
        if (currentQuantity == null) {
            insertCartItem(cartItem.copy(qty = 1))
        } else {
            insertCartItem(cartItem.copy(qty = currentQuantity + 1))

        }
    }
}