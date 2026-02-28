package com.matchmate.app.core.di

import android.content.Context
import androidx.room.Room
import com.matchmate.app.data.local.dao.MainDao
import com.matchmate.app.data.local.database.MainDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): MainDatabase {
        return Room.databaseBuilder(context, MainDatabase::class.java, "match_database").build()
    }

    @Provides
    @Singleton
    fun provideMoviesDao(database: MainDatabase): MainDao = database.getMainDao()
}