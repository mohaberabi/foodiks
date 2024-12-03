package com.mohaberabi.foodiks.core.data.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("product")
data class ProductEntity(
    @PrimaryKey(autoGenerate = false)
    val id: String,
    val name: String,
    val price: Double,
    val categoryName: String,
    val categoryId: String,
    val description: String,
    val image: String?
)