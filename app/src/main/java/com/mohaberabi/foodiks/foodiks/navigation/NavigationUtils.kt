package com.mohaberabi.foodiks.foodiks.navigation

import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavDestination.Companion.hierarchy
import kotlin.reflect.KClass


fun NavController.navigateBottom(
    item: Any
) = navigate(item) {
    currentBackStackEntry?.destination?.route?.let {
        popUpTo(it) {
            inclusive = true
            saveState = true
        }
    }

    launchSingleTop = true
    restoreState = true
}


fun NavDestination?.isCurrentRoute(route: KClass<*>): Boolean {
    return this?.hierarchy?.any {
        it.hasRoute(route)
    } ?: false
}