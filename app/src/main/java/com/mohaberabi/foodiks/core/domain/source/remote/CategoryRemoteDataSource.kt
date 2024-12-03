package com.mohaberabi.foodiks.core.domain.source.remote

import com.mohaberabi.foodiks.core.domain.model.CategoryModel

interface CategoryRemoteDataSource {
    suspend fun getAllCategories(): List<CategoryModel>
}