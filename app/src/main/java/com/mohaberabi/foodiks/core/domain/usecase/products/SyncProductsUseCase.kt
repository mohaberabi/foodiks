package com.mohaberabi.foodiks.core.domain.usecase.products

import com.mohaberabi.foodiks.core.domain.model.AppResult
import com.mohaberabi.foodiks.core.domain.repository.ProductRepository

class SyncProductsUseCase(
    private val productRepository: ProductRepository
) {
    suspend operator fun invoke(): AppResult<Unit> {
        return productRepository.sync()
    }
}