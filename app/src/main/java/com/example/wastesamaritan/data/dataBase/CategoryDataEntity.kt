package com.example.wastesamaritan.data.dataBase

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "not_segregated_data")
data class NotSegregatedDataEntity(
    @PrimaryKey val id: String,
    val capturedImageUris: List<String>,
    val totalWeight: Double,
    val weightCards: List<Double>,
    val rating: Double,
    val screenType: String
)

@Entity(tableName = "segregated_data")
data class SegregatedDataEntity(
    @PrimaryKey val id: String,
    val category: String,
    val capturedImageUris: List<String>,
    val totalWeight: Double,
    val weightCards: List<Double>,
    val rating: Double,
    val screenType: String
)
