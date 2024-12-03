package com.mohaberabi.foodiks.features.tables.presentation.screens

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.mohaberabi.foodiks.core.domain.model.CartModel
import com.mohaberabi.foodiks.features.tables.presentation.viewmodel.TablesState
import com.mohaberabi.foodiks.features.tables.presentation.viewmodel.TablesViewModel
import org.koin.androidx.compose.koinViewModel


@Composable
fun TablesScreenRoot(
    viewmodel: TablesViewModel = koinViewModel()
) {

    val tablesState by viewmodel.tablesState.collectAsStateWithLifecycle()
    val cartState by viewmodel.cartState.collectAsStateWithLifecycle()

}


@Composable
fun TablesScreen(
    modifier: Modifier = Modifier,
    cartState: CartModel,
    tablesState: TablesState,
) {


}