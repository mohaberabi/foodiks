package com.mohaberabi.foodiks.core.domain.usecase.categories

import com.mohaberabi.foodiks.core.domain.model.AppResult
import com.mohaberabi.foodiks.core.domain.repository.CategoryRepository

class RefreshCategoriesUseCase(
    private val categoryRepository: CategoryRepository
) {
    suspend operator fun invoke(forceRemote: Boolean): AppResult<Unit> {
        return categoryRepository.refresh(forceRemote)
    }
}