package com.mohaberabi.foodiks.core.common.util.extension

import com.mohaberabi.foodiks.R
import com.mohaberabi.foodiks.core.domain.model.error.DataError
import com.mohaberabi.foodiks.core.presentation.util.UiText


fun DataError.asUiText() = when (this) {
    DataError.CommonError.RetryAttemptExceeded -> R.string.retry_exced
    DataError.LocalError.DISK_FULL -> R.string.disk_full
    DataError.LocalError.IO_FAILURE -> R.string.io_fail
    DataError.LocalError.CONSTRAINT_FAILURE -> R.string.constraint_failed
    DataError.LocalError.DB_CORRUPTION -> R.string.db_curropted
    DataError.LocalError.TRANSACTION_FAILED -> R.string.txn_failed
    DataError.RemoteError.REQUEST_TIMEOUT -> R.string.request_timeout
    DataError.RemoteError.TOO_MANY_REQUESTS -> R.string.many_requests
    DataError.RemoteError.NO_INTERNET -> R.string.no_internet
    DataError.RemoteError.SERVER -> R.string.server_error
    DataError.RemoteError.SERIALIZATION -> R.string.server_error
    else -> R.string.unknown_error
}.let {
    UiText.StringResource(it)
}