package com.mohaberabi.foodiks.core.domain.model


data class CartModel(
    private val items: Map<String, CartItemModel> = mapOf()
) : Map<String, CartItemModel> by items {
    val cartTotal: Double = values.toList().sumOf { it.totalPrice }
}


