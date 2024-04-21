package com.example.wastesamaritan.data.roomdatabase

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import java.io.File

@Entity(tableName = "not_segregated_data")
data class NotSegregatedDataEntity(
    @PrimaryKey(autoGenerate = true) val primaryKey: Int = 0,
    val id: String,
    val capturedImageUris: List<String>,
    val weightCards: MutableList<Double>,
    val rating: Double,
    val audio:String?,
    val screenType: String
)

@Entity(tableName = "segregated_data")
data class SegregatedDataEntity(
    @PrimaryKey(autoGenerate = true) val primaryKey: Int = 0,
    val id: String,
    val category: String,
    val capturedImageUris: List<String>,
    val weightCards: MutableList<Double>,
    val rating: Double,
    val audio: String?,
    val screenType: String
)


