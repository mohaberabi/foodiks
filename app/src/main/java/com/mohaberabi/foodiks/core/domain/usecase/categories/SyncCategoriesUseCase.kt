package com.mohaberabi.foodiks.core.domain.usecase.categories

import com.mohaberabi.foodiks.core.domain.model.AppResult
import com.mohaberabi.foodiks.core.domain.repository.CategoryRepository

class SyncCategoriesUseCase(
    private val categoryRepository: CategoryRepository
) {
    suspend operator fun invoke(): AppResult<Unit> {
        return categoryRepository.sync()
    }
}