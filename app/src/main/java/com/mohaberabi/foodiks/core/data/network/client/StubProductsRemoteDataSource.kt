package com.mohaberabi.foodiks.core.data.network.client

import com.mohaberabi.foodiks.core.domain.model.CategoryModel
import com.mohaberabi.foodiks.core.domain.model.ProductModel
import com.mohaberabi.foodiks.core.domain.source.remote.ProductsRemoteDataSource
import kotlin.random.Random

class StubProductsRemoteDataSource : ProductsRemoteDataSource {
    override suspend fun getAllProducts(): List<ProductModel> {
        val categoryIds = listOf("1", "2", "3", "4", "5", "6", "7", "8")
        val productNames = listOf("Shrimp", "Fish", "Sepia")
        val products = mutableListOf<ProductModel>()

        repeat(50) { index ->
            val randomCategoryId = categoryIds.random()
            val randomProductName = productNames.random()
            products.add(
                ProductModel(
                    id = "product-${index + 1}",
                    name = "$randomProductName-${index + 1}",
                    description = "Delicious $randomProductName",
                    image = "https://cookswithsoul.com/wp-content/uploads/2024/04/seafood-boill-step-15-scaled.jpg",
                    price = Random.nextDouble(10.0, 1500.0),
                    category = CategoryModel(randomCategoryId, randomCategoryId)
                )
            )
        }

        return products
    }
}