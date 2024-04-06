package com.example.wastesamaritan.data.roomdatabase

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface NotSegregatedDataDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCategoryData(data: NotSegregatedDataEntity)


    @Query("SELECT * FROM not_segregated_data WHERE screenType = 'Not Segregated'")
    fun getNotSegregatedData(): LiveData<List<NotSegregatedDataEntity>>

    @Query("SELECT * FROM not_segregated_data WHERE id = :id")
    fun getDataById(id: String): LiveData<NotSegregatedDataEntity>
    @Query("UPDATE not_segregated_data SET capturedImageUris = :capturedImageUris, totalWeight = :totalWeight, weightCards = :weightCards, rating = :rating WHERE id = :id")
    suspend fun updateItem(id: String, capturedImageUris: List<String>, totalWeight: Double, weightCards: List<Double>, rating: Double)
}


@Dao
interface SegregatedDataDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCategoryData(data: SegregatedDataEntity)

    @Query("SELECT * FROM segregated_data WHERE screenType = 'Segregated'")
    fun getSegregatedData(): LiveData<List<SegregatedDataEntity>>

    @Query("SELECT * FROM segregated_data WHERE id = :id")
    fun getDataById(id: String): LiveData<SegregatedDataEntity>

    @Query("UPDATE segregated_data SET capturedImageUris = :capturedImageUris, totalWeight = :totalWeight, weightCards = :weightCards, rating = :rating WHERE id = :id")
    suspend fun updateItem(id: String, capturedImageUris: List<String>, totalWeight: Double, weightCards: List<Double>, rating: Double)
}