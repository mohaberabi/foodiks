package com.mohaberabi.foodiks.core.data.database.mapper

import com.mohaberabi.foodiks.core.data.database.entity.ProductFtsEntity
import com.mohaberabi.foodiks.core.domain.model.ProductFtsModel


val ProductFtsModel.toProductFtsEntity
    get() = ProductFtsEntity(
        productId = productId,
        productName = productName,
        productCategoryName = productCategoryName
    )


val ProductFtsEntity.toProductFtsModel
    get() = ProductFtsModel(
        productId = productId,
        productName = productName,
        productCategoryName = productCategoryName
    )