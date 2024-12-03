package com.mohaberabi.foodiks.core.domain.usecase.products

import com.mohaberabi.foodiks.core.domain.model.ProductModel
import com.mohaberabi.foodiks.core.domain.repository.ProductRepository
import kotlinx.coroutines.flow.Flow

class SearchProductsUseCase(
    private val productRepository: ProductRepository
) {
    operator fun invoke(
        query: String,
    ): Flow<List<ProductModel>> {

        return if (query.isEmpty()) {
            productRepository.getAllProducts()
        } else {
            productRepository.searchProducts(query)
        }
    }
}