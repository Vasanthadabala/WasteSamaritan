package com.example.wastesamaritan.data.roomdatabase

import android.app.Application
import android.net.Uri
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.room.Room
import kotlinx.coroutines.launch

class RoomDatabaseViewModel(application: Application) : AndroidViewModel(application) {
    private val notSegregatedDataDao : NotSegregatedDataDao
    private val segregatedDataDao : SegregatedDataDao

    init {
        val database: AppDatabase = Room.databaseBuilder(
            application,
            AppDatabase::class.java,
            "app_database"
        ).build()

        notSegregatedDataDao = database.notSegregatedDataDao()
        segregatedDataDao = database.segregatedDataDao()
    }

    fun getNotSegregatedDataByScreenType(): LiveData<List<NotSegregatedDataEntity>> {
        return notSegregatedDataDao.getNotSegregatedData()
    }

    fun getSegregatedDataByScreenType(): LiveData<List<SegregatedDataEntity>> {
        return segregatedDataDao.getSegregatedData()
    }

    fun getNotSegregatedDataById(id: String): LiveData<NotSegregatedDataEntity> {
        return notSegregatedDataDao.getDataById(id)
    }

    fun getSegregatedDataById(id: String): LiveData<SegregatedDataEntity> {
        return segregatedDataDao.getDataById(id)
    }


    fun insertNotSegregatedData(
        id: String,
        capturedImageUris: List<Uri>,
        weightCards: MutableList<Double>,
        rating: Double,
        screenType: String
    ) {
        val capturedImages = capturedImageUris.map { it.toString() }
        val notSegregatedDataEntity = NotSegregatedDataEntity(
            id = id,
            capturedImageUris = capturedImages,
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
        weightCards: MutableList<Double>,
        rating: Double,
        screenType: String
    ) {
        insertNotSegregatedData(id, capturedImageUris, weightCards, rating, screenType)
    }

    fun insertSegregatedData(
        id: String,
        category: String,
        capturedImageUris: List<Uri>,
        weightCards: MutableList<Double>,
        rating: Double,
        screenType: String
    ) {
        val capturedImages = capturedImageUris.map { it.toString() }
        val segregatedDataEntity = SegregatedDataEntity(
            id = id,
            category = category,
            capturedImageUris = capturedImages,
            weightCards = weightCards,
            rating = rating,
            screenType = screenType
        )
        viewModelScope.launch {
            segregatedDataDao.insertCategoryData(segregatedDataEntity)
        }
    }

    fun saveSegregatedData(
        id: String,
        category: String,
        capturedImageUris: List<Uri>,
        weightCards: MutableList<Double>,
        rating: Double,
        screenType: String
    ) {
        insertSegregatedData(id, category, capturedImageUris, weightCards, rating, screenType)
    }
}