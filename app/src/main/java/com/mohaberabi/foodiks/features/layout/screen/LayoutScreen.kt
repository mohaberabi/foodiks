package com.mohaberabi.foodiks.features.layout.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.mohaberabi.foodiks.core.presentation.compose.ActiveUserAppBar
import com.mohaberabi.foodiks.core.presentation.compose.AppScaffold
import com.mohaberabi.foodiks.core.presentation.compose.MainBottomBar
import com.mohaberabi.foodiks.features.layout.navigation.BottomNavItems


@Composable
fun LayoutScreen(
    onNavigateBottom: (BottomNavItems) -> Unit,
    selected: (BottomNavItems) -> Boolean,
    content: @Composable () -> Unit,
) {

    AppScaffold(
        topAppBar = {
            ActiveUserAppBar()
        },
        bottomAppBar = {
            MainBottomBar(
                onClick = onNavigateBottom,
                selected = selected
            )
        }
    ) { padding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            content()
        }

    }
}