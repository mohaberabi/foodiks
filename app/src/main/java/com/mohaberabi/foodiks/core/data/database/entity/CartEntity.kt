package com.mohaberabi.foodiks.core.data.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cart")
data class CartEntity(
    @PrimaryKey(autoGenerate = false) val id: String,
    val name: String,
    val price: Double,
    val qty: Int
)