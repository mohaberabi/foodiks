package com.mohaberabi.foodiks.core.data.repository

import com.mohaberabi.foodiks.core.domain.model.AppResult
import com.mohaberabi.foodiks.core.domain.model.CategoryModel
import com.mohaberabi.foodiks.core.domain.model.handleAppResult
import com.mohaberabi.foodiks.core.domain.repository.CategoryRepository
import com.mohaberabi.foodiks.core.domain.source.local.CategoryLocalDataSource
import com.mohaberabi.foodiks.core.domain.source.remote.CategoryRemoteDataSource
import kotlinx.coroutines.flow.Flow

class OfflineFirstCategoryRepository(
    private val categoryRemoteDataSource: CategoryRemoteDataSource,
    private val categoryLocalDataSource: CategoryLocalDataSource,
) : CategoryRepository {
    override fun getAllCategories(): Flow<List<CategoryModel>> =
        categoryLocalDataSource.getAllCategories()

    override suspend fun sync(): AppResult<Unit> = handleAppResult {
        val remote = categoryRemoteDataSource.getAllCategories()
        categoryLocalDataSource.insertAllCategories(remote)
    }

    override suspend fun refresh(
        forceRemote: Boolean,
    ): AppResult<Unit> {
        val shouldRefresh = categoryLocalDataSource.isEmpty() || forceRemote
        return if (shouldRefresh) {
            sync()
        } else {
            AppResult.Done(Unit)
        }
    }
}