package com.mohaberabi.foodiks.foodiks

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.Modifier
import com.mohaberabi.foodiks.core.common.util.extension.dismissAndShowSnackbar
import com.mohaberabi.foodiks.core.presentation.compose.EventCollector
import com.mohaberabi.foodiks.foodiks.navigation.FoodiksNavHost
import com.mohaberabi.foodiks.core.presentation.compose.AppScaffold
import com.mohaberabi.foodiks.core.presentation.util.DefaultSnackBarController

val LocalSnackBarController = compositionLocalOf { DefaultSnackBarController() }

@Composable
fun FoodiksComposedAppRoot(
    foodiksState: FoodiksAppState,
) {
    FoodiksComposedApp(
        foodiksState = foodiksState,
    )
}

@Composable
fun FoodiksComposedApp(
    foodiksState: FoodiksAppState,
) {
    val rootNavController = foodiksState.foodkisNavController
    val layoutNavController = foodiksState.layoutNavController
    val hostState = foodiksState.foodiksHostState

    CompositionLocalProvider(
        LocalSnackBarController provides DefaultSnackBarController(),
    ) {
        val snackBarController = LocalSnackBarController.current
        EventCollector(
            flow = snackBarController.messages,
        ) { message ->
            hostState.dismissAndShowSnackbar(
                message = message,
            )
        }

        AppScaffold(
            snackBarHostState = hostState,
        ) { padding ->
            Box(
                modifier = Modifier
                    .padding(padding)
                    .consumeWindowInsets(padding)
                    .windowInsetsPadding(
                        WindowInsets.safeDrawing.only(
                            WindowInsetsSides.Horizontal,
                        ),
                    )
                    .fillMaxSize()
            ) {
                FoodiksNavHost(
                    rootNavController = rootNavController,
                    layoutNavController = layoutNavController
                )

            }
        }
    }
}

