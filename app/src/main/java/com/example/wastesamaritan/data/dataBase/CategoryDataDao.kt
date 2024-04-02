package com.example.wastesamaritan.data.dataBase

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface CategoryDataDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCategoryData(categoryData: CategoryDataEntity)

    @Query("SELECT * FROM category_data WHERE screenType = :screenType")
    fun getCategoryDataByScreenType(screenType: String): LiveData<List<CategoryDataEntity>>

    @Query("SELECT * FROM category_data WHERE screenType = 'Segregated'")
    fun getSegregatedData(): LiveData<List<CategoryDataEntity>>

    @Query("SELECT * FROM category_data WHERE screenType = 'Not Segregated'")
    fun getNotSegregatedData(): LiveData<List<CategoryDataEntity>>
}