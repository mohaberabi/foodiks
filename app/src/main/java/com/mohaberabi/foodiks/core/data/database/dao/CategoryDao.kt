package com.mohaberabi.foodiks.core.data.database.dao

import androidx.room.*
import com.mohaberabi.foodiks.core.data.database.entity.CategoryEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface CategoryDao {

    @Upsert
    suspend fun insertAllCategories(categories: List<CategoryEntity>)

    @Query("SELECT COUNT(*) = 0 FROM product")
    suspend fun isEmpty(): Boolean

    @Query("SELECT * FROM category")
    fun getAllCategories(): Flow<List<CategoryEntity>>
}