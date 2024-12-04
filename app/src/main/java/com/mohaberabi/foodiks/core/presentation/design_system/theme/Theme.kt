package com.mohaberabi.foodiks.core.presentation.design_system.theme


import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable


@Composable
fun FoodiksTheme(
    content: @Composable () -> Unit
) {

    MaterialTheme(
        colorScheme = appThemeLightScheme,
        typography = Typography,
        content = content
    )
}