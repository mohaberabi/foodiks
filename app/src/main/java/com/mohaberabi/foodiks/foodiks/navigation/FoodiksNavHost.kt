package com.mohaberabi.foodiks.foodiks.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.mohaberabi.foodiks.features.layout.navigation.layoutScreen
import com.mohaberabi.foodiks.features.layout.navigation.navigateToLayout
import com.mohaberabi.foodiks.features.onboarding.navigation.OnBoardingRoute
import com.mohaberabi.foodiks.features.onboarding.navigation.onBoardingScreen


@Composable
fun FoodiksNavHost(
    rootNavController: NavHostController,
    layoutNavController: NavHostController,
    modifier: Modifier = Modifier
) {

    NavHost(
        modifier = modifier,
        navController = rootNavController,
        startDestination = OnBoardingRoute
    ) {
        onBoardingScreen(
            onGetStarted = { rootNavController.navigateToLayout() }
        )
        layoutScreen(
            layoutNavController = layoutNavController
        )
    }
}


