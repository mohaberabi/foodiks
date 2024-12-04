package com.mohaberabi.foodiks.foodiks

import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.CoroutineScope


@Stable
data class FoodiksAppState(
    val foodiksCoroutineScope: CoroutineScope,
    val foodkisNavController: NavHostController,
    val layoutNavController: NavHostController,
    val foodiksHostState: SnackbarHostState
)

@Composable
fun rememberFoodiksAppState(
    scope: CoroutineScope = rememberCoroutineScope(),
    rootController: NavHostController = rememberNavController(),
    layoutNavController: NavHostController = rememberNavController(),

    hostState: SnackbarHostState = SnackbarHostState(),
): FoodiksAppState {
    return remember(
        scope,
        rootController,
        layoutNavController
    ) {
        FoodiksAppState(
            foodiksHostState = hostState,
            foodiksCoroutineScope = scope,
            foodkisNavController = rootController,
            layoutNavController = layoutNavController
        )
    }
}