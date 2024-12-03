package com.mohaberabi.foodiks.foodiks

import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import com.mohaberabi.foodiks.core.presentation.compose.EventCollector
import com.mohaberabi.foodiks.core.common.navigation.BottomNavRoutes
import com.mohaberabi.foodiks.core.common.navigation.FoodiksNavHost
import com.mohaberabi.foodiks.core.presentation.util.DefaultSnackBarController
import com.mohaberabi.foodiks.features.tables.presentation.navigation.tablesScreen
import kotlinx.coroutines.launch

val LocalSnackBarController = compositionLocalOf { DefaultSnackBarController() }

@Composable
fun FoodiksApp(
    startRoute: Any = BottomNavRoutes.Tables.route,
    foodiksState: FoodiksAppState,
) {
    val controller = foodiksState.foodkisNavController
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
        FoodiksNavHost(
            controller = controller,
        )
    }


}