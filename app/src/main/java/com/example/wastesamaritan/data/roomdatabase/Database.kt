package com.example.wastesamaritan.data.roomdatabase

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

@Database(entities = [NotSegregatedDataEntity::class, SegregatedDataEntity::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)

abstract class AppDatabase : RoomDatabase() {
    abstract fun notSegregatedDataDao(): NotSegregatedDataDao
    abstract fun segregatedDataDao(): SegregatedDataDao
}


