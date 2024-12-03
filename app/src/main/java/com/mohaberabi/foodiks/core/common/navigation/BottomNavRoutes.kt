package com.mohaberabi.foodiks.core.common.navigation

import com.mohaberabi.foodiks.features.tables.presentation.navigation.TablesRoute
import kotlin.reflect.KClass

enum class BottomNavRoutes(
    val route: KClass<*>
) {

    Tables(TablesRoute::class),
    Orders(TablesRoute::class),
    Menu(TablesRoute::class),
    Settings(TablesRoute::class)
}