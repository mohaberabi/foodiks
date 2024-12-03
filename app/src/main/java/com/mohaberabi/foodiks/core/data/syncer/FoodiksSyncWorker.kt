package com.mohaberabi.foodiks.core.data.syncer

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.mohaberabi.foodiks.core.domain.model.AppResult
import com.mohaberabi.foodiks.core.domain.usecase.categories.SyncCategoriesUseCase
import com.mohaberabi.foodiks.core.domain.usecase.products.SyncProductsUseCase
import com.mohaberabi.foodiks.core.domain.util.DispatchersProvider
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.ensureActive
import kotlinx.coroutines.joinAll
import kotlinx.coroutines.withContext

class FoodiksSyncWorker(
    context: Context,
    params: WorkerParameters,
    private val dispatchers: DispatchersProvider,
    private val syncCategories: SyncCategoriesUseCase,
    private val syncProducts: SyncProductsUseCase,
) : CoroutineWorker(context, params) {
    override suspend fun doWork(): Result {
        return withContext(dispatchers.io) {
            try {
                val syncedAll = awaitAll(
                    async { syncCategories() },
                    async { syncProducts() }
                ).all { it is AppResult.Done }
                if (syncedAll) {
                    Result.success()
                } else {
                    Result.retry()
                }
            } catch (e: Exception) {
                ensureActive()
                Result.retry()
            }
        }
    }
}