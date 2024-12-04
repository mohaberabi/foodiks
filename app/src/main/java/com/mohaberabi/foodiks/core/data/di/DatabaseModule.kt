package com.mohaberabi.foodiks.core.data.di

import androidx.room.Room
import com.mohaberabi.foodiks.core.data.database.AppDatabase
import com.mohaberabi.foodiks.core.data.database.dao.CartDao
import com.mohaberabi.foodiks.core.data.database.dao.CategoryDao
import com.mohaberabi.foodiks.core.data.database.dao.ProductFtsDao
import com.mohaberabi.foodiks.core.data.database.dao.ProductsDao
import org.koin.dsl.module


val databaseModule = module {
    single<AppDatabase> {
        Room.databaseBuilder(
            get(),
            AppDatabase::class.java,
            "foodiks.db"
        ).build()
    }

    single<ProductsDao> {
        get<AppDatabase>().productDao()
    }
    single<ProductFtsDao> {
        get<AppDatabase>().productFtsDao()
    }
    single<CategoryDao> {
        get<AppDatabase>().categoryDao()
    }
    single<CartDao> {
        get<AppDatabase>().cartDao()
    }
}