package com.mohaberabi.foodiks.core.domain.usecase.connectivity

import com.mohaberabi.foodiks.core.domain.source.connectivity.AppConnectivityManager

class CheckConnectivityUseCase(
    private val connectivityManager: AppConnectivityManager,
) {


    operator fun invoke() = connectivityManager.isConnected
}