package com.matchmate.app.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.matchmate.app.data.local.dao.MainDao
import com.matchmate.app.data.local.entity.PersonEntity

@Database(entities = [PersonEntity::class], version = 1, exportSchema = false)
abstract class MainDatabase: RoomDatabase() {
    abstract fun getMainDao(): MainDao
}