package com.mohaberabi.foodiks.core.common.util

import android.util.Log
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.retryWhen
import kotlin.math.pow


fun <T> Flow<T>.retryExponentBackOff(
    maxRetry: Int = 3,
    initDelayMillis: Long = 1000L,
    maxDelayMillis: Long = 15000L,
    retryPredicate: (Throwable) -> Boolean = { true },
    whileAttmpting: suspend FlowCollector<T>.() -> Unit = {}
): Flow<T> = retryWhen { cause, attempt ->
    if (attempt >= maxRetry || !retryPredicate(cause)) {
        false
    } else {
        Log.d("retryExponentBackOff", "Attempt To Retry A Failed Flow ${attempt}")
        whileAttmpting()
        val nextDelay = (initDelayMillis * 2.0.pow(attempt.toDouble())).toLong()
            .coerceAtMost(maxDelayMillis)
        delay(nextDelay)
        true
    }
}