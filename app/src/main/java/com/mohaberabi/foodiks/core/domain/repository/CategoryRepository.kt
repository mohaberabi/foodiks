package com.mohaberabi.foodiks.core.domain.repository

import com.mohaberabi.foodiks.core.domain.model.CategoryModel
import com.mohaberabi.foodiks.core.domain.syncer.Refreshable
import com.mohaberabi.foodiks.core.domain.syncer.Syncable
import kotlinx.coroutines.flow.Flow

interface CategoryRepository : Syncable, Refreshable {
    fun getAllCategories(): Flow<List<CategoryModel>>
}