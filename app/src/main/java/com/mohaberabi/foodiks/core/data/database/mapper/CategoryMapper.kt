package com.mohaberabi.foodiks.core.data.database.mapper

import com.mohaberabi.foodiks.core.data.database.entity.CategoryEntity
import com.mohaberabi.foodiks.core.domain.model.CategoryModel


val CategoryEntity.toCategoryModel
    get() = CategoryModel(
        id = id,
        name = name
    )

val CategoryModel.toCategoryEntity
    get() = CategoryEntity(
        id = id,
        name = name
    )