package com.mohaberabi.foodiks.foodiks

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mohaberabi.foodiks.core.domain.usecase.connectivity.CheckConnectivityUseCase
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn

class FoodiksViewModel(
    checkConnectivity: CheckConnectivityUseCase
) : ViewModel() {
    val isConnected = checkConnectivity().stateIn(
        viewModelScope,
        SharingStarted.Eagerly,
        true
    )
}