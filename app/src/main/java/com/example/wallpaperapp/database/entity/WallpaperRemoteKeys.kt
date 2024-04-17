package com.example.wallpaperapp.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.wallpaperapp.utils.Constants.WALLPAPER_REMOTE_KEYS_TABLE_NAME

@Entity(tableName = WALLPAPER_REMOTE_KEYS_TABLE_NAME)
data class WallpaperRemoteKeys(
    @PrimaryKey(autoGenerate = false)
    val id: String,
    val prevPage: Int?,
    val nextPage: Int?
)
