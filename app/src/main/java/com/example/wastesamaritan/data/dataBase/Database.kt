package com.example.wastesamaritan.data.dataBase

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [CategoryDataEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun categoryDataDao(): CategoryDataDao
}


