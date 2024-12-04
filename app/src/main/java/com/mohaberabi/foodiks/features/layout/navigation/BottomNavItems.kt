package com.mohaberabi.foodiks.features.layout.navigation

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.mohaberabi.foodiks.R
import com.mohaberabi.foodiks.features.menu.screen.MenuRoute
import com.mohaberabi.foodiks.features.orders.navigation.OrdersRoute
import com.mohaberabi.foodiks.features.settings.navigation.SettingsRoute
import com.mohaberabi.foodiks.features.tables.presentation.navigation.TablesRoute


enum class BottomNavItems(
    @StringRes val label: Int,
    @DrawableRes val icon: Int
) {

    Tables(R.string.tables, R.drawable.ic_fork),
    Orders(R.string.orders, R.drawable.ic_book),
    Menu(R.string.menu, R.drawable.ic_menu),
    Settings(R.string.settings, R.drawable.ic_settings)
}


val BottomNavItems.route: Any
    get() = when (this) {
        BottomNavItems.Tables -> TablesRoute
        BottomNavItems.Orders -> OrdersRoute
        BottomNavItems.Menu -> MenuRoute
        BottomNavItems.Settings -> SettingsRoute
    }