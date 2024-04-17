package com.example.wallpaperapp.data.source.remote

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.wallpaperapp.data.service.WallpaperService
import com.example.wallpaperapp.data.source.paging.WallpaperRemoteMediator
import com.example.wallpaperapp.database.db.WallpaperDatabase
import com.example.wallpaperapp.database.entity.WallpaperEntity
import com.example.wallpaperapp.utils.Constants.ITEMS_PER_PAGE
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class WallpaperDataSourceImpl @Inject constructor(
    private val wallpaperService: WallpaperService,
    private val wallpaperDatabase: WallpaperDatabase
) : WallpaperDataSource {

    @OptIn(ExperimentalPagingApi::class)
    override fun getAllWallpapers(): Flow<PagingData<WallpaperEntity>> {
        val pagingSourceFactory = { wallpaperDatabase.getWallpaperDao().getAllWallpapers() }
        return Pager(
            config = PagingConfig(pageSize = ITEMS_PER_PAGE),
            remoteMediator = WallpaperRemoteMediator(
                wallpaperService = wallpaperService,
                wallpaperDatabase = wallpaperDatabase
            ),
            pagingSourceFactory = pagingSourceFactory
        ).flow.flowOn(Dispatchers.IO)
    }
}