package com.mohaberabi.foodiks.core.data.di

import com.mohaberabi.foodiks.core.data.network.client.StubCategoriesRemoteDataSource
import com.mohaberabi.foodiks.core.data.network.client.StubProductsRemoteDataSource
import com.mohaberabi.foodiks.core.data.network.factory.HttpClientFactory
import com.mohaberabi.foodiks.core.domain.source.remote.CategoryRemoteDataSource
import com.mohaberabi.foodiks.core.domain.source.remote.ProductsRemoteDataSource
import io.ktor.client.HttpClient
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module


val networkModule = module {
    single<HttpClient> { HttpClientFactory.create() }
    singleOf(::StubCategoriesRemoteDataSource) { bind<CategoryRemoteDataSource>() }
    singleOf(::StubProductsRemoteDataSource) { bind<ProductsRemoteDataSource>() }
}