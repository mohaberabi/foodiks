package com.mohaberabi.foodiks.core.data.di


import com.mohaberabi.foodiks.core.data.database.source.RoomCartLocalDataSource
import com.mohaberabi.foodiks.core.data.database.source.RoomCategoryLocalDataSource
import com.mohaberabi.foodiks.core.data.database.source.RoomProductFtsLocalDataSource
import com.mohaberabi.foodiks.core.data.database.source.RoomProductsLocalDataSource
import com.mohaberabi.foodiks.core.domain.source.local.CartLocalDataSource
import com.mohaberabi.foodiks.core.domain.source.local.CategoryLocalDataSource
import com.mohaberabi.foodiks.core.domain.source.local.ProductFtsLocalDataSource
import com.mohaberabi.foodiks.core.domain.source.local.ProductsLocalDataSource
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module


val coreLocalDataSourceModule = module {


    singleOf(::RoomCartLocalDataSource) { bind<CartLocalDataSource>() }
    singleOf(::RoomCategoryLocalDataSource) { bind<CategoryLocalDataSource>() }
    singleOf(::RoomProductsLocalDataSource) { bind<ProductsLocalDataSource>() }
    singleOf(::RoomProductFtsLocalDataSource) { bind<ProductFtsLocalDataSource>() }


}





