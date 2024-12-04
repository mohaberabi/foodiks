package com.mohaberabi.foodiks.core.data.database.ext

import android.database.sqlite.SQLiteFullException
import com.mohaberabi.foodiks.core.domain.model.error.AppErrorModel
import com.mohaberabi.foodiks.core.domain.model.error.AppException
import com.mohaberabi.foodiks.core.domain.model.error.DataError
import kotlinx.coroutines.ensureActive
import java.io.IOException
import kotlin.coroutines.coroutineContext


suspend inline fun <T> executeDatabaseOperation(operation: () -> T): T {
    return try {
        operation()
    } catch (e: android.database.sqlite.SQLiteConstraintException) {
        throw AppException(
            AppErrorModel(
                error = DataError.LocalError.CONSTRAINT_FAILURE,
                message = "Constraint violation occurred in the database",
                cause = e
            )
        )
    } catch (e: android.database.sqlite.SQLiteDatabaseCorruptException) {
        throw AppException(
            AppErrorModel(
                error = DataError.LocalError.DB_CORRUPTION,
                message = "Database corruption detected",
                cause = e
            )
        )
    } catch (e: android.database.SQLException) {
        throw AppException(
            AppErrorModel(
                error = DataError.LocalError.TRANSACTION_FAILED,
                message = "Database transaction failed",
                cause = e
            )
        )
    } catch (e: SQLiteFullException) {
        throw AppException(
            AppErrorModel(
                error = DataError.LocalError.DISK_FULL,
                message = "Disk Full Can Not Write Data To Database",
                cause = e
            )
        )
    } catch (e: IOException) {
        throw AppException(
            AppErrorModel(
                error = DataError.LocalError.IO_FAILURE,
                message = "IO failure during database operation",
                cause = e
            )
        )
    } catch (e: Exception) {
        coroutineContext.ensureActive()
        e.printStackTrace()
        throw AppException(
            AppErrorModel(
                error = DataError.LocalError.UNKNOWN,
                message = "An unknown database error occurred",
                cause = e
            )
        )
    }
}