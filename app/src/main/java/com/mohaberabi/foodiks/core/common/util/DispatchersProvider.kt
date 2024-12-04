package com.mohaberabi.foodiks.core.common.util

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

interface DispatchersProvider {
    val main: CoroutineDispatcher
    val default: CoroutineDispatcher
    val unconfined: CoroutineDispatcher
    val io: CoroutineDispatcher
}


class DefaultDispatchersProvider(
) : DispatchersProvider {
    override val main: CoroutineDispatcher
        get() = Dispatchers.Main
    override val default: CoroutineDispatcher
        get() = Dispatchers.Default
    override val io: CoroutineDispatcher
        get() = Dispatchers.IO
    override val unconfined: CoroutineDispatcher
        get() = Dispatchers.Unconfined
}