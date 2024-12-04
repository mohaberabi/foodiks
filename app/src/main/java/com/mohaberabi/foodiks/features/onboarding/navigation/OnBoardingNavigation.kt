package com.mohaberabi.foodiks.features.onboarding.navigation


import androidx.activity.compose.BackHandler
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.mohaberabi.foodiks.features.onboarding.screen.OnBoardingScreen
import kotlinx.serialization.Serializable


@Serializable
data object OnBoardingRoute


fun NavGraphBuilder.onBoardingScreen(
    onGetStarted: () -> Unit
) = composable<OnBoardingRoute> {
    BackHandler {

    }
    OnBoardingScreen(
        onGetStarted = onGetStarted
    )
}