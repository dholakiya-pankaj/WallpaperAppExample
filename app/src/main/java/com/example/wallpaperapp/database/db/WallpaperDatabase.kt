package com.example.wallpaperapp.database.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.wallpaperapp.database.daos.WallpaperDao
import com.example.wallpaperapp.database.daos.WallpaperRemoteKeysDao
import com.example.wallpaperapp.database.entity.WallpaperEntity
import com.example.wallpaperapp.database.entity.WallpaperRemoteKeys

@Database(entities = [WallpaperEntity::class, WallpaperRemoteKeys::class], version = 1, exportSchema = false)
abstract class WallpaperDatabase : RoomDatabase() {
    abstract fun getWallpaperDao(): WallpaperDao
    abstract fun getRemoteKeysDao(): WallpaperRemoteKeysDao
}