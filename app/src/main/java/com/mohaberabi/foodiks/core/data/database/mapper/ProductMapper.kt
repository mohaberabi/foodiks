package com.mohaberabi.foodiks.core.data.database.mapper

import com.mohaberabi.foodiks.core.data.database.entity.ProductEntity
import com.mohaberabi.foodiks.core.domain.model.CategoryModel
import com.mohaberabi.foodiks.core.domain.model.ProductModel

val ProductEntity.toProductModel
    get() = ProductModel(
        id = id,
        name = name,
        price = price,
        category = CategoryModel(id = categoryId, name = categoryName),
        description = description,
        image = image
    )

val ProductModel.toProductEntity
    get() = ProductEntity(
        id = id,
        name = name,
        price = price,
        categoryName = category.name,
        categoryId = category.id,
        description = description,
        image = image
    )