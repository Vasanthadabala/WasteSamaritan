package com.example.wastesamaritan.data.roomdatabase

import android.app.Application
import android.net.Uri
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import androidx.room.Room
import kotlinx.coroutines.launch

class RoomDatabaseViewModel(application: Application) : AndroidViewModel(application) {
    private val notSegregatedDataDao: NotSegregatedDataDao
    private val segregatedDataDao: SegregatedDataDao

    private val database: AppDatabase by lazy {
        Room.databaseBuilder(
            application.applicationContext,
            AppDatabase::class.java, "app_database"
        ).build()
    }

    init {
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


    fun insertNotSegregatedData(
        id: String,
        capturedImageUris: List<Uri>,
        totalWeight: Double,
        weightCards: List<Double>,
        rating: Double,
        screenType: String
    ) {
        val capturedImages = capturedImageUris.map { it.toString() }
        val notSegregatedDataEntity = NotSegregatedDataEntity(
            id = id,
            capturedImageUris = capturedImages,
            totalWeight = totalWeight,
            weightCards = weightCards,
            rating = rating,
            screenType = screenType
        )
        viewModelScope.launch {
            notSegregatedDataDao.insertCategoryData(notSegregatedDataEntity)
        }
    }

    fun saveNotSegregatedData(
        id: String,
        capturedImageUris: List<Uri>,
        totalWeight: Double,
        weightCards: List<Double>,
        rating: Double,
        screenType: String
    ) {
        insertNotSegregatedData(id, capturedImageUris, totalWeight, weightCards, rating, screenType)
    }

    fun insertSegregatedData(
        id: String,
        category: String,
        capturedImageUris: List<Uri>,
        totalWeight: Double,
        weightCards: List<Double>,
        rating: Double,
        screenType: String
    ) {
        val capturedImages = capturedImageUris.map { it.toString() }
        val segregatedDataEntity = SegregatedDataEntity(
            id = id,
            category = category,
            capturedImageUris = capturedImages,
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