package com.mohaberabi.foodiks.core.data.di

import com.mohaberabi.foodiks.foodiks.FoodiksViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module


val coreViewModelModule = module {


    viewModelOf(::FoodiksViewModel)
}