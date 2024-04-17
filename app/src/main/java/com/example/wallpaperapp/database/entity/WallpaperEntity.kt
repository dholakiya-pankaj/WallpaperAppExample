package com.example.wallpaperapp.database.entity

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.wallpaperapp.domain.model.Urls
import com.example.wallpaperapp.domain.model.User
import com.example.wallpaperapp.utils.Constants.WALLPAPER_TABLE_NAME
import kotlinx.serialization.Serializable

@Serializable
@Entity(tableName = WALLPAPER_TABLE_NAME)
data class WallpaperEntity(
    @PrimaryKey(autoGenerate = false)
    val wallpaperId: String,
    @Embedded
    val urls: Urls? = null,
    val likes: Int? = null,
    @Embedded
    val user: User? = null
)
