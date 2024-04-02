package com.example.wastesamaritan.data.viewmodel

import android.app.Application
import android.net.Uri
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import androidx.room.Room
import com.example.wastesamaritan.data.dataBase.AppDatabase
import com.example.wastesamaritan.data.dataBase.CategoryDataDao
import com.example.wastesamaritan.data.dataBase.CategoryDataEntity
import kotlinx.coroutines.launch
import java.util.UUID

class RoomDatabaseViewmodel(application: Application): AndroidViewModel(application) {
    private val dao: CategoryDataDao

    init {
        val database: AppDatabase =
            Room.databaseBuilder(application, AppDatabase::class.java, "database1")
                .build()
        dao = database.categoryDataDao()
    }

    fun getCategoryDataByScreenType(screenType:String):LiveData<List<CategoryDataEntity>>{
        return dao.getCategoryDataByScreenType(screenType)
    }

//    fun getSegregatedData(): LiveData<List<CategoryDataEntity>> {
//        return dao.getSegregatedData()
//    }
//
//    fun getNotSegregatedData(): LiveData<List<CategoryDataEntity>> {
//        return dao.getNotSegregatedData()
//    }
//
//    fun saveSegregatedData(category: String, capturedImageUris: List<String>, totalWeight: Double, rating: Double) {
//        insertCategoryData(category, capturedImageUris, totalWeight, rating, "Segregated")
//    }
//
//    fun saveNotSegregatedData(category: String, capturedImageUris: List<String>, totalWeight: Double, rating: Double) {
//        insertCategoryData(category, capturedImageUris, totalWeight, rating, "Not Segregated")
//    }


//    private fun insertCategoryData(category: String, capturedImageUris: List<String>, totalWeight: Double, rating: Double, screenType: String) {
//        val categoryDataEntity = CategoryDataEntity(
//            id = UUID.randomUUID().toString(),
//            category = category,
//            capturedImageUris = capturedImageUris,
//            totalWeight = totalWeight,
//            weightCards = emptyList(), // Assuming weightCards are initially empty
//            rating = rating,
//            screenType = screenType
//        )
//        viewModelScope.launch {
//            dao.insertCategoryData(categoryDataEntity)
//        }
//    }

    fun saveData(category: String, capturedImageUris: List<String>, totalWeight: Double, rating: Double, screenType: String) {
        insertCategoryData(category, capturedImageUris, totalWeight, rating, screenType)
    }
    
    private fun insertCategoryData(category: String, capturedImageUris: List<String>, totalWeight: Double, rating: Double, screenType: String) {
        val categoryDataEntity = CategoryDataEntity(
            id = UUID.randomUUID().toString(),
            category = category,
            capturedImageUris = capturedImageUris,
            totalWeight = totalWeight,
            weightCards = emptyList(), // Assuming weightCards are initially empty
            rating = rating,
            screenType = screenType
        )
        viewModelScope.launch {
            dao.insertCategoryData(categoryDataEntity)
        }
    }

}