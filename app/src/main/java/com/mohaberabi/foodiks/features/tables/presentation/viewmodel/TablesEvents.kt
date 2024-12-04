package com.mohaberabi.foodiks.features.tables.presentation.viewmodel

sealed interface TablesEvents {


    data object OrderDone : TablesEvents
    data class Error(val error: String) : TablesEvents

}