package com.mohaberabi.foodiks.core.data.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Fts4


@Fts4
@Entity("productFts")
data class ProductFtsEntity(
    @ColumnInfo(name = "productId")
    val productId: String,
    @ColumnInfo(name = "productName")
    val productName: String,
    @ColumnInfo(name = "productCategoryName")
    val productCategoryName: String,
)
