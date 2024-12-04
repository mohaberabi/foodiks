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
        productName = name.trim().lowercase(),
        productCategoryName = category.name.trim().lowercase()
    )

val ProductModel.toCartModel
    get() = CartItemModel(
        id = id,
        name = name,
        price = price,
        qty = 0
    )