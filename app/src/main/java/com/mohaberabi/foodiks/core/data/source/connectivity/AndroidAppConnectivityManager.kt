package com.mohaberabi.foodiks.core.data.source.connectivity

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import androidx.core.content.getSystemService
import com.mohaberabi.foodiks.core.common.util.DispatchersProvider
import com.mohaberabi.foodiks.core.domain.source.connectivity.AppConnectivityManager
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flowOn

class AndroidAppConnectivityManager(
    context: Context,
    private val dispatchers: DispatchersProvider,
) : AppConnectivityManager {

    private val connectivityManager =
        requireNotNull(context.getSystemService<ConnectivityManager>())

    override val isConnected: Flow<Boolean>
        get() = callbackFlow {
            val callback = object : ConnectivityManager.NetworkCallback() {
                override fun onCapabilitiesChanged(
                    network: Network,
                    networkCapabilities: NetworkCapabilities
                ) {
                    super.onCapabilitiesChanged(network, networkCapabilities)
                    val connected = networkCapabilities.hasCapability(
                        NetworkCapabilities.NET_CAPABILITY_VALIDATED
                    )
                    trySend(connected)
                }

                override fun onUnavailable() {
                    super.onUnavailable()
                    trySend(false)
                }

                override fun onLost(network: Network) {
                    super.onLost(network)
                    trySend(false)
                }

                override fun onAvailable(network: Network) {
                    super.onAvailable(network)
                    trySend(true)
                }
            }

            connectivityManager.registerDefaultNetworkCallback(callback)

            awaitClose {
                connectivityManager.unregisterNetworkCallback(callback)
            }
        }.distinctUntilChanged().flowOn(dispatchers.io)

}