package com.mohaberabi.foodiks.features.tables.presentation.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.mohaberabi.foodiks.features.tables.presentation.screens.TablesScreenRoot
import kotlinx.serialization.Serializable


@Serializable
data object TablesRoute


fun NavGraphBuilder.tablesScreen(
) = composable<TablesRoute>() {
    TablesScreenRoot()
}