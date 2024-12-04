package com.mohaberabi.foodiks.core.domain.model

import com.mohaberabi.foodiks.core.common.util.extension.clockFormat
import com.mohaberabi.foodiks.core.common.util.extension.toCurrencyFormat
import java.util.Locale


data class CartModel(
    private val items: Map<String, CartItemModel> = mapOf()
) : Map<String, CartItemModel> by items {
    private val cartTotal: Double = values.toList().sumOf { it.totalPrice }


    val cartTotalFormatted: String get() = cartTotal.toCurrencyFormat()

    val cartSizeFormatted: String get() = size.clockFormat()
}


