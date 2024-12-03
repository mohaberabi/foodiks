package com.mohaberabi.foodiks.core.data.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity("category")
data class CategoryEntity(

    @PrimaryKey(autoGenerate = false)
    val id: String,
    val name: String
)