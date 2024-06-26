package com.example.wastesamaritan.data.roomdatabase

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface NotSegregatedDataDao {
    @Insert
    suspend fun insertCategoryData(data: NotSegregatedDataEntity)

    @Delete
    suspend fun deleteData(data: NotSegregatedDataEntity)

    @Query("DELETE FROM not_segregated_data WHERE primaryKey = :id")
    suspend fun deleteListById(id: Int)

    @Query("SELECT * FROM not_segregated_data WHERE screenType = 'Not Segregated' ")
    fun getNotSegregatedData(): LiveData<List<NotSegregatedDataEntity>>

    @Query("SELECT * FROM not_segregated_data WHERE id = :id")
    fun getDataById(id: String): LiveData<NotSegregatedDataEntity>

    @Query("SELECT * FROM not_segregated_data WHERE screenType = 'Not Segregated' ORDER BY id ASC")
    fun getNotSegregatedDataSortedById(): LiveData<List<NotSegregatedDataEntity>>

    @Query("UPDATE not_segregated_data SET capturedImageUris = :capturedImageUris, weightCards = :weightCards, rating = :rating WHERE id = :id")
    suspend fun updateItem(id: String, capturedImageUris: List<String>, weightCards: List<Double>, rating: Double)
}


@Dao
interface SegregatedDataDao {
    @Insert
    suspend fun insertCategoryData(data: SegregatedDataEntity)

    @Delete
    suspend fun deleteData(data: SegregatedDataEntity)

    @Query("DELETE FROM segregated_data WHERE primaryKey = :id")
    suspend fun deleteListById(id: Int)

    @Query("SELECT * FROM segregated_data WHERE screenType = 'Segregated'")
    fun getSegregatedData(): LiveData<List<SegregatedDataEntity>>

    @Query("SELECT * FROM segregated_data WHERE id = :id")
    fun getDataById(id: String): LiveData<SegregatedDataEntity>

    @Query("SELECT * FROM segregated_data WHERE screenType = 'Segregated' ORDER BY id ASC")
    fun getSegregatedDataSortedById(): LiveData<List<SegregatedDataEntity>>

    @Query("SELECT * FROM segregated_data WHERE id = :id AND category = :category")
    fun getDataByCategory(id: String, category: String): LiveData<List<SegregatedDataEntity>>

    @Query("UPDATE segregated_data SET capturedImageUris = :capturedImageUris, category =:category, weightCards = :weightCards, rating = :rating,audio= :audio WHERE id = :id")
    suspend fun updateItem(id: String, category: String, capturedImageUris: List<String>, weightCards: List<Double>, rating: Double,audio: String?)
}