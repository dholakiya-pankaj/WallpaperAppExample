package com.example.wallpaperapp.data.source.paging

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.example.wallpaperapp.data.mapper.toDomain
import com.example.wallpaperapp.data.service.WallpaperService
import com.example.wallpaperapp.database.db.WallpaperDatabase
import com.example.wallpaperapp.database.entity.WallpaperEntity
import com.example.wallpaperapp.database.entity.WallpaperRemoteKeys
import com.example.wallpaperapp.utils.Constants

@ExperimentalPagingApi
class WallpaperRemoteMediator(
    private val wallpaperService: WallpaperService,
    private val wallpaperDatabase: WallpaperDatabase
) : RemoteMediator<Int, WallpaperEntity>() {

    private val wallpaperDao = wallpaperDatabase.getWallpaperDao()
    private val remoteKeysDao = wallpaperDatabase.getRemoteKeysDao()

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, WallpaperEntity>
    ): MediatorResult {
        return try {
            val currentPage = when (loadType) {
                LoadType.REFRESH -> {
                    val remoteKeys = getRemoteKeyClosestToCurrentPosition(state)
                    remoteKeys?.nextPage?.minus(1) ?: 1
                }
                LoadType.PREPEND -> {
                    val remoteKeys = getRemoteKeyForFirstItem(state)
                    val prevPage = remoteKeys?.prevPage
                        ?: return MediatorResult.Success(
                            endOfPaginationReached = remoteKeys != null
                        )
                    prevPage
                }
                LoadType.APPEND -> {
                    val remoteKeys = getRemoteKeyForLastItem(state)
                    val nextPage = remoteKeys?.nextPage
                        ?: return MediatorResult.Success(
                            endOfPaginationReached = remoteKeys != null
                        )
                    nextPage
                }
            }

            val response = wallpaperService.getWallpapers(currentPage, Constants.ITEMS_PER_PAGE)
            val endOfPaginationReached = response.isEmpty()

            val prevPage = if (currentPage == 1) null else currentPage - 1
            val nextPage = if(endOfPaginationReached) null else currentPage + 1

            wallpaperDatabase.withTransaction {
                if (loadType == LoadType.REFRESH) {
                    wallpaperDao.deleteAllWallpapers()
                    remoteKeysDao.deleteAllRemoteKeys()
                }
                val keys = response.map { wallpaper ->
                    WallpaperRemoteKeys(
                        id = wallpaper.id.toString(),
                        prevPage = prevPage,
                        nextPage = nextPage
                    )
                }
                remoteKeysDao.addAllRemoteKeys(remoteKeys = keys)
                wallpaperDao.insertWallpapers(wallpapers = response.toDomain())
            }
            MediatorResult.Success(endOfPaginationReached = endOfPaginationReached)
        } catch (e: Exception) {
            return MediatorResult.Error(e)
        }
    }

    private fun getRemoteKeyClosestToCurrentPosition(
        state: PagingState<Int, WallpaperEntity>
    ): WallpaperRemoteKeys? {
        return state.anchorPosition?.let { position ->
            state.closestItemToPosition(position)?.wallpaperId?.let { id ->
                remoteKeysDao.getRemoteKeys(id = id)
            }
        }
    }

    private fun getRemoteKeyForFirstItem(
        state: PagingState<Int, WallpaperEntity>
    ): WallpaperRemoteKeys? {
        return state.pages.firstOrNull { it.data.isNotEmpty() }?.data?.firstOrNull()
            ?.let { unsplashImage ->
                remoteKeysDao.getRemoteKeys(id = unsplashImage.wallpaperId)
            }
    }

    private fun getRemoteKeyForLastItem(
        state: PagingState<Int, WallpaperEntity>
    ): WallpaperRemoteKeys? {
        return state.pages.lastOrNull { it.data.isNotEmpty() }?.data?.lastOrNull()
            ?.let { unsplashImage ->
                remoteKeysDao.getRemoteKeys(id = unsplashImage.wallpaperId)
            }
    }
}


