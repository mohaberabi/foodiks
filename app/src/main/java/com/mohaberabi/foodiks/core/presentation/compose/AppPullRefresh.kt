//package com.mohaberabi.foodiks.core.presentation.compose
//
//import androidx.compose.foundation.layout.Box
//import androidx.compose.foundation.layout.BoxScope
//import androidx.compose.material3.ExperimentalMaterial3Api
//import androidx.compose.material3.pulltorefresh.PullToRefreshDefaults
//import androidx.compose.material3.pulltorefresh.PullToRefreshDefaults.Indicator
//import androidx.compose.material3.pulltorefresh.PullToRefreshState
//import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
//import androidx.compose.runtime.Composable
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.unit.Dp
//
//
//@OptIn(ExperimentalMaterial3Api::class)
//@Composable
//fun PullToRefreshBox(
//    isRefreshing: Boolean,
//    onRefresh: () -> Unit,
//    modifier: Modifier = Modifier,
//    state: PullToRefreshState = rememberPullToRefreshState(),
//    contentAlignment: Alignment = Alignment.TopStart,
//    indicator: @Composable BoxScope.() -> Unit = {
//        Indicator(
//            modifier = Modifier.align(Alignment.TopCenter),
//            state = state
//        )
//    },
//    content: @Composable BoxScope.() -> Unit
//) {
//    Box(
//        modifier.pullToRefresh(state = state, isRefreshing = isRefreshing, onRefresh = onRefresh),
//        contentAlignment = contentAlignment
//    ) {
//        content()
//        indicator()
//    }
//}
//
//@ExperimentalMaterial3Api
//fun Modifier.pullToRefresh(
//    isRefreshing: Boolean,
//    state: PullToRefreshState,
//    enabled: Boolean = true,
//    threshold: Dp = PullToRefreshDefaults.PositionalThreshold,
//    onRefresh: () -> Unit,
//): Modifier =
//    this then PullToRefreshElement(
//        state = state,
//        isRefreshing = isRefreshing,
//        enabled = enabled,
//        onRefresh = onRefresh,
//        threshold = threshold
//    )