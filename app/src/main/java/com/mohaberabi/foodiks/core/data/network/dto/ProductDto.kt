package com.mohaberabi.foodiks.core.data.network.dto

import kotlinx.serialization.Serializable


@Serializable
data class ProductDto(
    val id: String?,
    val name: String?,
    val price: Double?,
    val category: CategoryDto?,
    val description: String?,
    val image: String?
)
