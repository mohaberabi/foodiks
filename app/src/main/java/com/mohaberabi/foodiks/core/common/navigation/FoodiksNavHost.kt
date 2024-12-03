package com.mohaberabi.foodiks.core.common.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.mohaberabi.foodiks.features.tables.presentation.navigation.TablesRoute
import com.mohaberabi.foodiks.features.tables.presentation.navigation.tablesScreen


@Composable
fun FoodiksNavHost(
    controller: NavHostController,
) {

    NavHost(
        navController = controller,
        startDestination = TablesRoute
    ) {
        tablesScreen()
    }
}