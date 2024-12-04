package com.mohaberabi.foodiks.features.orders.navigation

import OrdersScreen
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import kotlinx.serialization.Serializable

@Serializable
data object OrdersRoute

fun NavGraphBuilder.ordersScreen(
) = composable<OrdersRoute> { OrdersScreen() }