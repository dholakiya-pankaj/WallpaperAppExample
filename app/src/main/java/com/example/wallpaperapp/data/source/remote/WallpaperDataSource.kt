package com.example.wallpaperapp.data.source.remote

import androidx.paging.PagingData
import com.example.wallpaperapp.database.entity.WallpaperEntity
import kotlinx.coroutines.flow.Flow

interface WallpaperDataSource {

    fun getAllWallpapers(): Flow<PagingData<WallpaperEntity>>
}