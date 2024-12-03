package com.mohaberabi.foodiks.foodiks

import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.mohaberabi.foodiks.core.common.navigation.BottomNavRoutes
import kotlinx.coroutines.CoroutineScope


@Stable
data class FoodiksAppState(
    val foodiksCoroutineScope: CoroutineScope,
    val foodkisNavController: NavHostController,
    val foodiksHostState: SnackbarHostState
) {
    val currentDestination: NavDestination?
        @Composable get() = foodkisNavController
            .currentBackStackEntryAsState().value?.destination

    val currentBottomRoute: BottomNavRoutes?
        @Composable get() {
            return BottomNavRoutes.entries.firstOrNull { bottomRoute ->
                currentDestination?.hasRoute(route = bottomRoute.route) == true
            }
        }

}


@Composable
fun rememberFoodiksAppState(
    scope: CoroutineScope = rememberCoroutineScope(),
    controller: NavHostController = rememberNavController(),
    hostState: SnackbarHostState = SnackbarHostState(),
): FoodiksAppState {
    return remember(
        scope,
        controller,
    ) {
        FoodiksAppState(
            foodiksHostState = hostState,
            foodiksCoroutineScope = scope,
            foodkisNavController = controller
        )
    }
}