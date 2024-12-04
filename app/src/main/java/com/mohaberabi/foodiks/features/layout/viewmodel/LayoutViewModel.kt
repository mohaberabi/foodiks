package com.mohaberabi.foodiks.features.layout.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mohaberabi.foodiks.core.domain.usecase.connectivity.CheckConnectivityUseCase
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn

class LayoutViewModel(
    checkConnectivity: CheckConnectivityUseCase
) : ViewModel() {
    val isConnected = checkConnectivity().stateIn(
        viewModelScope,
        SharingStarted.Eagerly,
        true
    )
}