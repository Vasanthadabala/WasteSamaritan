package com.example.wastesamaritan.data.dataBase

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "category_data")
data class CategoryDataEntity(
    @PrimaryKey val id: String,
    val category: String,
    val capturedImageUris: List<String>,
    val totalWeight: Double,
    val weightCards: List<Double>,
    val rating: Double,
    val screenType: String
)