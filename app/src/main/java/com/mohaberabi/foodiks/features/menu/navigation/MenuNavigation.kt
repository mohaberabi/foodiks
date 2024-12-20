package com.mohaberabi.foodiks.features.menu.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.mohaberabi.foodiks.features.menu.screen.MenuScreen
import kotlinx.serialization.Serializable

@Serializable
data object MenuRoute


fun NavGraphBuilder.menuScreen() = composable<MenuRoute> { MenuScreen() }