package com.example.wastesamaritan.data.dataBase

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [NotSegregatedDataEntity::class, SegregatedDataEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun notSegregatedDataDao(): NotSegregatedDataDao
    abstract fun segregatedDataDao(): SegregatedDataDao
}


