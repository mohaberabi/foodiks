package com.mohaberabi.foodiks.core.data.di

import com.mohaberabi.foodiks.core.domain.usecase.cart.AddToCartUseCase
import com.mohaberabi.foodiks.core.domain.usecase.cart.ClearCartUseCase
import com.mohaberabi.foodiks.core.domain.usecase.cart.GetCartUseCase
import com.mohaberabi.foodiks.core.domain.usecase.categories.GetAllCategoriesUseCase
import com.mohaberabi.foodiks.core.domain.usecase.categories.RefreshCategoriesUseCase
import com.mohaberabi.foodiks.core.domain.usecase.categories.SyncCategoriesUseCase
import com.mohaberabi.foodiks.core.domain.usecase.products.GetAllProductsUseCase
import com.mohaberabi.foodiks.core.domain.usecase.products.RefreshProductsUseCase
import com.mohaberabi.foodiks.core.domain.usecase.products.SearchProductsUseCase
import com.mohaberabi.foodiks.core.domain.usecase.products.SyncProductsUseCase

import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module


val coreUseCaseModule = module {

    singleOf(::SearchProductsUseCase)
    singleOf(::GetAllCategoriesUseCase)
    singleOf(::RefreshCategoriesUseCase)
    singleOf(::SyncCategoriesUseCase)
    singleOf(::GetAllProductsUseCase)
    singleOf(::RefreshProductsUseCase)
    singleOf(::SyncProductsUseCase)
    singleOf(::AddToCartUseCase)
    singleOf(::ClearCartUseCase)
    singleOf(::GetCartUseCase)


}