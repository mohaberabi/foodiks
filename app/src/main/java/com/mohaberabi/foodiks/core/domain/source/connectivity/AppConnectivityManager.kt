package com.mohaberabi.foodiks.core.domain.source.connectivity

import kotlinx.coroutines.flow.Flow

interface AppConnectivityManager {
    val isConnected: Flow<Boolean>
}