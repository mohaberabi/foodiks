package com.mohaberabi.foodiks.core.data.network.client

import com.mohaberabi.foodiks.core.domain.model.CategoryModel
import com.mohaberabi.foodiks.core.domain.source.remote.CategoryRemoteDataSource
import kotlinx.coroutines.delay

class StubCategoriesRemoteDataSource : CategoryRemoteDataSource {
    override suspend fun getAllCategories(): List<CategoryModel> {
        delay(500)
        return listOf(
            CategoryModel(
                "1",
                "Fresh Fish"
            ),
            CategoryModel(
                "2",
                "Crustaceans"
            ),
            CategoryModel(
                "3",
                "Fish"
            ),
            CategoryModel(
                "4",
                "Pasta & Rice"
            ),
            CategoryModel(
                "5",
                "Casseroles"
            ),
            CategoryModel(
                "6",
                "Salad"
            ),
            CategoryModel(
                "7",
                "Extras"
            ),
            CategoryModel(
                "8",
                "Drink"
            ),
        )
    }
}