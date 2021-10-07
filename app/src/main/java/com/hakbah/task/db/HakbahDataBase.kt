package com.hakbah.task.db

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(version = 1, entities = [Post::class])
abstract class HakbahDataBase : RoomDatabase() {
    abstract fun hakbahDao(): HakbahDao
}