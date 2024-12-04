package com.mohaberabi.foodiks.features.tables.presentation.viewmodel

import com.mohaberabi.foodiks.core.presentation.util.UiText

sealed interface TablesEvents {


    data object OrderDone : TablesEvents
    data class Error(val error: UiText) : TablesEvents

}