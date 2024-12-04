package com.mohaberabi.foodiks.core.common.util

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.retryWhen
import kotlin.math.pow


fun <T> Flow<T>.retryExponentBackOff(
    maxRetry: Int = 3,
    initDelayMillis: Long = 500L,
    maxDelayMillis: Long = 5000L,
    retryPredicate: (Throwable) -> Boolean = { true }
): Flow<T> = retryWhen { cause, attempt ->
    if (attempt >= maxRetry || !retryPredicate(cause)) {
        false
    } else {
        val nextDelay = (initDelayMillis * 2.0.pow(attempt.toDouble())).toLong()
            .coerceAtMost(maxDelayMillis)
        delay(nextDelay)
        true
    }
}