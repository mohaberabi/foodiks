package com.mohaberabi.foodiks.core.data.di


import android.system.Os.bind
import com.mohaberabi.foodiks.core.data.source.local.RoomCartLocalDataSource
import com.mohaberabi.foodiks.core.data.source.local.RoomCategoryLocalDataSource
import com.mohaberabi.foodiks.core.data.source.local.RoomProductFtsLocalDataSource
import com.mohaberabi.foodiks.core.data.source.local.RoomProductsLocalDataSource
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





