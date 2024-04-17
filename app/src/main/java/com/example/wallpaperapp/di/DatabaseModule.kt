package com.example.wallpaperapp.di

import android.content.Context
import androidx.room.Room
import com.example.wallpaperapp.database.db.WallpaperDatabase
import com.example.wallpaperapp.utils.Constants.DATABASE_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Singleton
    @Provides
    fun provideWallpaperDB(
        @ApplicationContext context: Context
    ): WallpaperDatabase = Room.databaseBuilder(
        context,
        WallpaperDatabase::class.java,
        DATABASE_NAME
    ).build()

    @Singleton
    @Provides
    fun provideWallpaperDao(database: WallpaperDatabase) = database.getWallpaperDao()
}