package com.mohaberabi.foodiks.core.domain.model.error

sealed interface DataError : AppError {
    enum class RemoteError : DataError {
        REQUEST_TIMEOUT,
        TOO_MANY_REQUESTS,
        NO_INTERNET,
        SERVER,
        SERIALIZATION,
        UNKNOWN
    }

    enum class CommonError : DataError {
        UNKNOWN,
        RetryAttemptExceeded
    }

    enum class LocalError : DataError {
        DISK_FULL,
        IO_FAILURE,
        UNKNOWN
    }
}