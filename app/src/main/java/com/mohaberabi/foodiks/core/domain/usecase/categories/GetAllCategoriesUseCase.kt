package com.mohaberabi.foodiks.core.domain.usecase.categories

import com.mohaberabi.foodiks.core.domain.model.CategoryModel
import com.mohaberabi.foodiks.core.domain.repository.CategoryRepository
import kotlinx.coroutines.flow.Flow

class GetAllCategoriesUseCase(
    private val categoryRepository: CategoryRepository
) {
    operator fun invoke(): Flow<List<CategoryModel>> = categoryRepository.getAllCategories()
}


