package com.mohaberabi.foodiks.foodiks.navigation

import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import kotlin.reflect.KClass


fun NavController.navigateBottom(
    item: Any
) = navigate(item) {
    popUpTo(graph.findStartDestination().id) {
        saveState = true
    }
    launchSingleTop = true
    restoreState = true
}


fun NavDestination?.isCurrentRoute(route: KClass<*>): Boolean {
    return this?.hierarchy?.any {
        it.hasRoute(route)
    } ?: false
}