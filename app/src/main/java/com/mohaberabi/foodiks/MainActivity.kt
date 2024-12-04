package com.mohaberabi.foodiks

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.compositionLocalOf
import com.mohaberabi.foodiks.core.presentation.design_system.theme.FoodiksTheme
import com.mohaberabi.foodiks.core.presentation.util.DefaultSnackBarController
import com.mohaberabi.foodiks.foodiks.FoodiksApp
import com.mohaberabi.foodiks.foodiks.FoodiksAppRoot
import com.mohaberabi.foodiks.foodiks.rememberFoodiksAppState


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FoodiksTheme {
                val foodiksState = rememberFoodiksAppState()
                FoodiksAppRoot(
                    foodiksState = foodiksState,
                )
            }
        }
    }
}

