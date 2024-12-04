package com.mohaberabi.foodiks.features.layout.navigation

import androidx.activity.compose.BackHandler
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import com.mohaberabi.foodiks.features.layout.screen.LayoutScreen
import com.mohaberabi.foodiks.features.tables.presentation.navigation.TablesRoute
import com.mohaberabi.foodiks.foodiks.navigation.isCurrentRoute
import com.mohaberabi.foodiks.foodiks.navigation.navigateBottom
import kotlinx.serialization.Serializable


@Serializable
data object LayoutRoute


fun NavGraphBuilder.layoutScreen(
    layoutNavController: NavHostController,
) = composable<LayoutRoute> {
    val currentDestination: NavDestination? = layoutNavController
        .currentBackStackEntryAsState().value?.destination

    BackHandler(
        enabled = currentDestination.isCurrentRoute(TablesRoute::class),
    ) {

    }
    LayoutScreen(
        onNavigateBottom = { bottom ->
            layoutNavController.navigateBottom(bottom.route)
        },
        selected = { bottom ->
            currentDestination?.isCurrentRoute(bottom.route::class) ?: false
        },
    ) {
        BottomRoutesNavHost(
            controller = layoutNavController,
        )
    }
}

fun NavController.navigateToLayout() = navigate(LayoutRoute) {
    popUpTo(0) { inclusive = true }
    launchSingleTop = true
}