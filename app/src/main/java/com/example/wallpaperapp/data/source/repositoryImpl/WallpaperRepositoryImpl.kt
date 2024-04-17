package com.example.wallpaperapp.data.source.repositoryImpl

import androidx.paging.PagingData
import com.example.wallpaperapp.data.source.remote.WallpaperDataSource
import com.example.wallpaperapp.database.entity.WallpaperEntity
import com.example.wallpaperapp.domain.WallpapersRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class WallpaperRepositoryImpl @Inject constructor(
   private val wallpaperDataSource: WallpaperDataSource
) : WallpapersRepository {

    override fun getAllWallpapers(): Flow<PagingData<WallpaperEntity>> {
        return wallpaperDataSource.getAllWallpapers()
    }
}