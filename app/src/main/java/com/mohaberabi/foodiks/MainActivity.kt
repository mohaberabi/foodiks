package com.mohaberabi.foodiks

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.mohaberabi.foodiks.core.presentation.design_system.theme.FoodiksTheme
import com.mohaberabi.foodiks.foodiks.FoodiksComposedAppRoot
import com.mohaberabi.foodiks.foodiks.rememberFoodiksAppState


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        enableEdgeToEdge()
        setContent {
            FoodiksTheme {
                val foodiksState = rememberFoodiksAppState()
                FoodiksComposedAppRoot(
                    foodiksState = foodiksState,
                )
            }
        }
    }
}

