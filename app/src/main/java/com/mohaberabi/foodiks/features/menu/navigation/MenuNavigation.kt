package com.mohaberabi.foodiks.features.menu.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.mohaberabi.foodiks.features.menu.screen.MenuRoute
import com.mohaberabi.foodiks.features.menu.screen.MenuScreen


fun NavGraphBuilder.menuScreen() = composable<MenuRoute> { MenuScreen() }