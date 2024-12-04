package com.mohaberabi.foodiks.features.tables.data.di

import androidx.lifecycle.viewmodel.compose.viewModel
import com.mohaberabi.foodiks.features.tables.presentation.viewmodel.TablesViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module


val tablesModule = module {
    viewModelOf(::TablesViewModel)
}

