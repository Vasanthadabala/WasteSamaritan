package com.example.wastesamaritan.data.roomdatabase

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName = "not_segregated_data")
data class NotSegregatedDataEntity(
    @PrimaryKey val id: String,
    val capturedImageUris: List<String>,
    val weightCards: MutableList<Double>,
    val rating: Double,
    val screenType: String
)

@Entity(tableName = "segregated_data",
    primaryKeys = ["id", "category"],
    indices = [Index(value = ["category"], unique = true)])
data class SegregatedDataEntity(
    val id: String,
    val category: String,
    val capturedImageUris: List<String>,
    val weightCards: MutableList<Double>,
    val rating: Double,
    val screenType: String
)

