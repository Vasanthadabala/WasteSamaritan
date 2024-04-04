package com.example.wastesamaritan.data.viewmodel

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import androidx.room.Room
import com.example.wastesamaritan.data.dataBase.AppDatabase
import com.example.wastesamaritan.data.dataBase.NotSegregatedDataEntity
import com.example.wastesamaritan.data.dataBase.NotSegregatedDataDao
import com.example.wastesamaritan.data.dataBase.SegregatedDataEntity
import com.example.wastesamaritan.data.dataBase.SegregatedDataDao
import kotlinx.coroutines.launch
import java.util.UUID

class RoomDatabaseViewmodel(application: Application) : AndroidViewModel(application) {
    private val notSegregatedDataDao: NotSegregatedDataDao
    private val segregatedDataDao: SegregatedDataDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getInstance(application: Application): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    application.applicationContext,
                    AppDatabase::class.java,
                    "app_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
    init {
        val database: AppDatabase = getInstance(application)
        notSegregatedDataDao = database.notSegregatedDataDao()
        segregatedDataDao = database.segregatedDataDao()
    }

    fun getNotSegregatedDataByScreenType(): LiveData<List<NotSegregatedDataEntity>> {
        return notSegregatedDataDao.getNotSegregatedData()
    }

    fun getSegregatedDataByScreenType(): LiveData<List<SegregatedDataEntity>> {
        return segregatedDataDao.getSegregatedData()
    }

    fun getNotSegregatedDataById(id: String):LiveData<NotSegregatedDataEntity>{
        return notSegregatedDataDao.getDataById(id)
    }

    fun getSegregatedDataById(id: String):LiveData<SegregatedDataEntity>{
        return segregatedDataDao.getDataById(id)
    }



    fun saveNotSegregatedData(
        id: String,
        capturedImageUris: List<String>,
        totalWeight: Double,
        weightCards: List<Double>,
        rating: Double,
        screenType: String
    ) {
        val notSegregatedDataEntity = NotSegregatedDataEntity(
            id = id,
            capturedImageUris = capturedImageUris,
            totalWeight = totalWeight,
            weightCards = weightCards,
            rating = rating,
            screenType = screenType
        )
        viewModelScope.launch {
            notSegregatedDataDao.insertCategoryData(notSegregatedDataEntity)
        }
    }

    fun saveSegregatedData(
        id: String,
        category: String,
        capturedImageUris: List<String>,
        totalWeight: Double,
        weightCards: List<Double>,
        rating: Double,
        screenType: String
    ) {
        val segregatedDataEntity = SegregatedDataEntity(
            id = id,
            category = category,
            capturedImageUris = capturedImageUris,
            totalWeight = totalWeight,
            weightCards = weightCards,
            rating = rating,
            screenType = screenType
        )
        viewModelScope.launch {
            segregatedDataDao.insertCategoryData(segregatedDataEntity)
        }
    }
}