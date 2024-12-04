package com.mohaberabi.foodiks.core.domain.usecase.products

import com.mohaberabi.foodiks.core.domain.model.AppResult
import com.mohaberabi.foodiks.core.domain.repository.ProductRepository

class RefreshProductsUseCase(
    private val productRepository: ProductRepository
) {
    suspend operator fun invoke(forceRemote: Boolean): AppResult<Unit> =
        productRepository.refresh(forceRemote)
}