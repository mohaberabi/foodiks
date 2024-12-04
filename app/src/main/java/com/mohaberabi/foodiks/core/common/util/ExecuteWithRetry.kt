package com.mohaberabi.foodiks.core.common.util

import android.util.Log
import com.mohaberabi.foodiks.core.domain.model.error.AppErrorModel
import com.mohaberabi.foodiks.core.domain.model.error.AppException
import com.mohaberabi.foodiks.core.domain.model.error.DataError
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.delay


suspend fun <T> executeWithRetry(
    times: Int = 3,
    delayMillis: Long = 500,
    maxDelayMillis: Long = 5000,
    execute: suspend () -> T,
): T {
    var currentAttempt = 0
    var currentDelay = delayMillis
    var lastException: Throwable? = null
    while (currentAttempt < times) {
        Log.d("executeWithRetry", "Making the $currentAttempt Execution")
        try {
            return execute()
        } catch (e: Exception) {
            if (e is CancellationException) throw e
            lastException = e
            currentAttempt++
            if (currentAttempt < times) {
                delay(currentDelay)
                currentDelay = if (currentDelay * 2 > maxDelayMillis) {
                    maxDelayMillis
                } else {
                    currentDelay * 2
                }
            }
        }
    }
    throw lastException ?: AppException(
        error = AppErrorModel(
            message = "Retry failed without an exception",
            error = DataError.CommonError.RetryAttemptExceeded
        )
    )

}