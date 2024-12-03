package com.mohaberabi.foodiks.core.domain.model

data class ProductModel(

    val id: String,
    val name: String,
    val price: Double,
    val category: CategoryModel,
    val description: String,
    val image: String?
)

val ProductModel.toFtsModel
    get() = ProductFtsModel(
        productId = id,
        productName = name,
        productCategoryName = category.name
    )