package com.example.wallpaperapp.domain

import androidx.paging.PagingData
import com.example.wallpaperapp.database.entity.WallpaperEntity
import kotlinx.coroutines.flow.Flow

interface WallpapersRepository {

    fun getAllWallpapers(): Flow<PagingData<WallpaperEntity>>
}