package com.mohaberabi.foodiks.core.data.network.mapper

import com.mohaberabi.foodiks.core.data.network.dto.CategoryDto
import com.mohaberabi.foodiks.core.data.network.dto.ProductDto
import com.mohaberabi.foodiks.core.domain.model.CategoryModel
import com.mohaberabi.foodiks.core.domain.model.ProductModel


fun ProductDto.toProductModel() = ProductModel(
    id = id ?: "",
    name = name ?: "",
    description = description ?: "",
    price = price ?: 0.01,
    category = category?.toCategoryModel() ?: CategoryModel("", ""),
    image = image
)

fun CategoryDto.toCategoryModel() = CategoryModel(id, name)