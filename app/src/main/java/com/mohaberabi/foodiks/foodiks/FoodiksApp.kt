package com.mohaberabi.foodiks.foodiks

import androidx.activity.compose.BackHandler
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.mohaberabi.foodiks.core.presentation.compose.EventCollector
import com.mohaberabi.foodiks.foodiks.navigation.FoodiksNavHost
import com.mohaberabi.foodiks.core.presentation.compose.AppScaffold
import com.mohaberabi.foodiks.core.presentation.compose.NoConnectionTopBar
import com.mohaberabi.foodiks.core.presentation.design_system.theme.Spacing
import com.mohaberabi.foodiks.core.presentation.util.DefaultSnackBarController
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel

val LocalSnackBarController = compositionLocalOf { DefaultSnackBarController() }

@Composable
fun FoodiksAppRoot(
    foodiksState: FoodiksAppState,
    viewmodel: FoodiksViewModel = koinViewModel()
) {
    val connected by viewmodel.isConnected.collectAsStateWithLifecycle()
  
    FoodiksApp(
        foodiksState = foodiksState,
        isConnected = connected
    )
}

@Composable
fun FoodiksApp(
    foodiksState: FoodiksAppState,
    isConnected: Boolean
) {
    val rootNavController = foodiksState.foodkisNavController
    val layoutNavController = foodiksState.layoutNavController
    val scope = foodiksState.foodiksCoroutineScope
    val hostState = foodiksState.foodiksHostState
    val onShowSnackBar: (String) -> Unit = { message ->
        scope.launch {
            hostState.showSnackbar(message = message)
        }
    }

    CompositionLocalProvider(
        LocalSnackBarController provides DefaultSnackBarController(),
    ) {

        val snackBarController = LocalSnackBarController.current
        EventCollector(
            snackBarController.messages,
        ) { message ->
            onShowSnackBar(message)
        }

        AppScaffold(
            snackBarHostState = hostState,
        ) { padding ->
            Column(
                modifier = Modifier
                    .padding(padding)
                    .fillMaxSize()
            ) {
                AnimatedVisibility(visible = !isConnected) {
                    NoConnectionTopBar()
                }

                FoodiksNavHost(
                    rootNavController = rootNavController,
                    layoutNavController = layoutNavController
                )
            }


        }

    }


}


