package com.example.wastesamaritan.data.roomdatabase

import android.app.Application
import android.net.Uri
import android.util.Log
import android.widget.Toast
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.room.Room
import kotlinx.coroutines.launch
import java.io.File

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
        audio:File?,
        screenType: String
    ) {
        val capturedImages = capturedImageUris.map { it.toString() }
        val audioPath = audio?.absolutePath
        val notSegregatedDataEntity = NotSegregatedDataEntity(
            id = id,
            capturedImageUris = capturedImages,
            weightCards = weightCards,
            rating = rating,
            audio = audioPath,
            screenType = screenType
        )
        viewModelScope.launch {
            try {
                notSegregatedDataDao.insertCategoryData(notSegregatedDataEntity)
            }catch (e:Throwable){
                e.printStackTrace()
                Log.e("notSegregatedData", "Error occurred while saving data", e)
                val context = getApplication<Application>().applicationContext
                Toast.makeText(context, "Error occurred while saving data", Toast.LENGTH_SHORT).show()
            }
        }
    }

    fun saveNotSegregatedData(
        id: String,
        capturedImageUris: List<Uri>,
        weightCards: MutableList<Double>,
        rating: Double,
        audio:File?,
        screenType: String
    ) {
        insertNotSegregatedData(id, capturedImageUris, weightCards, rating,audio, screenType)
    }

    fun insertSegregatedData(
        id: String,
        category: String,
        capturedImageUris: List<Uri>,
        weightCards: MutableList<Double>,
        rating: Double,
        audio:File?,
        screenType: String
    ) {
        val capturedImages = capturedImageUris.map { it.toString() }
        val audioPath = audio?.absolutePath
        val idCategory = "$id$category" // Combine ID and Category
        val segregatedDataEntity = SegregatedDataEntity(
            idCategory = idCategory,
            id = id,
            category = category,
            capturedImageUris = capturedImages,
            weightCards = weightCards,
            audio = audioPath,
            rating = rating,
            screenType = screenType
        )
        viewModelScope.launch {
            try {
                segregatedDataDao.insertCategoryData(segregatedDataEntity)
            }catch (e:Throwable){
                e.printStackTrace()
                Log.e("SegregatedData", "Error occurred while saving data", e)
                val context = getApplication<Application>().applicationContext
                Toast.makeText(context, "Error occurred while saving data", Toast.LENGTH_SHORT).show()
            }
        }
    }

    fun saveSegregatedData(
        id: String,
        category: String,
        capturedImageUris: List<Uri>,
        weightCards: MutableList<Double>,
        rating: Double,
        audio: File?,
        screenType: String
    ) {
        insertSegregatedData(id, category, capturedImageUris, weightCards, rating, audio, screenType)
    }
}