package com.mohaberabi.foodiks.core.domain.model


data class CartItemModel(
    val id: String,
    val name: String,
    val price: Double,
    val qty: Int
) {
    val totalPrice: Double get() = qty * price
}