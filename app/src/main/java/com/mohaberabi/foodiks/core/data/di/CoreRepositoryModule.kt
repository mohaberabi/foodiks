package com.mohaberabi.foodiks.core.data.di

import com.mohaberabi.foodiks.core.data.repository.OfflineFirstCartRepository
import com.mohaberabi.foodiks.core.data.repository.OfflineFirstCategoryRepository
import com.mohaberabi.foodiks.core.data.repository.OfflineFirstProductRepository
import com.mohaberabi.foodiks.core.domain.repository.CartRepository
import com.mohaberabi.foodiks.core.domain.repository.CategoryRepository
import com.mohaberabi.foodiks.core.domain.repository.ProductRepository
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module


val coreRepositoryModule = module {
    singleOf(::OfflineFirstCartRepository) { bind<CartRepository>() }
    singleOf(::OfflineFirstProductRepository) { bind<ProductRepository>() }
    singleOf(::OfflineFirstCategoryRepository) { bind<CategoryRepository>() }

}