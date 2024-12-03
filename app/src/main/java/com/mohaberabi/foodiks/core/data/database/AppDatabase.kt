package com.mohaberabi.foodiks.core.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.mohaberabi.foodiks.core.data.database.dao.CartDao
import com.mohaberabi.foodiks.core.data.database.dao.CategoryDao
import com.mohaberabi.foodiks.core.data.database.dao.ProductFtsDao
import com.mohaberabi.foodiks.core.data.database.dao.ProductsDao
import com.mohaberabi.foodiks.core.data.database.entity.CartEntity
import com.mohaberabi.foodiks.core.data.database.entity.CategoryEntity
import com.mohaberabi.foodiks.core.data.database.entity.ProductEntity
import com.mohaberabi.foodiks.core.data.database.entity.ProductFtsEntity


@Database(
    entities = [
        ProductEntity::class,
        CategoryEntity::class,
        ProductFtsEntity::class,
        CartEntity::class
    ],
    version = 1
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun cartDao(): CartDao

    abstract fun categoryDao(): CategoryDao
    abstract fun productDao(): ProductsDao
    abstract fun productFtsDao(): ProductFtsDao
}