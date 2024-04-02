package com.example.wastesamaritan.data.dataBase

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface NotSegregatedDataDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCategoryData(categoryData: NotSegregatedDataEntity)


    @Query("SELECT * FROM not_segregated_data WHERE screenType = 'Not Segregated'")
    fun getNotSegregatedData(): LiveData<List<NotSegregatedDataEntity>>
}


@Dao
interface SegregatedDataDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCategoryData(categoryData: SegregatedDataEntity)

    @Query("SELECT * FROM segregated_data WHERE screenType = 'Segregated'")
    fun getSegregatedData(): LiveData<List<SegregatedDataEntity>>

}