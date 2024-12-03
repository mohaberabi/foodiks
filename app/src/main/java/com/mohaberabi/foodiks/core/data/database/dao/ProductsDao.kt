package com.mohaberabi.foodiks.core.data.database.dao

import androidx.room.*
import com.mohaberabi.foodiks.core.data.database.entity.ProductEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ProductsDao {

    @Upsert
    suspend fun addProducts(products: List<ProductEntity>)

    @Query("SELECT COUNT(*) = 0 FROM product")
    suspend fun isEmpty(): Boolean

    @Query("SELECT * FROM product")
    fun getAllProducts(): Flow<List<ProductEntity>>

    @Query("SELECT * FROM product WHERE id IN (:ids)")
    fun getAllProductsByIds(ids: Set<String>): Flow<List<ProductEntity>>
}