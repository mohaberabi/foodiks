package com.mohaberabi.foodiks.features.layout.di

import com.mohaberabi.foodiks.features.layout.viewmodel.LayoutViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module


val layoutModule = module {


    viewModelOf(::LayoutViewModel)

}