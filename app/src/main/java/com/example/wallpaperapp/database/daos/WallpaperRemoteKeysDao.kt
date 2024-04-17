package com.example.wallpaperapp.database.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.wallpaperapp.database.entity.WallpaperRemoteKeys
import com.example.wallpaperapp.utils.Constants

@Dao
interface WallpaperRemoteKeysDao {

    @Query("SELECT * FROM ${Constants.WALLPAPER_REMOTE_KEYS_TABLE_NAME} WHERE id =:id")
    fun getRemoteKeys(id: String): WallpaperRemoteKeys

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addAllRemoteKeys(remoteKeys: List<WallpaperRemoteKeys>)

    @Query("DELETE FROM ${Constants.WALLPAPER_REMOTE_KEYS_TABLE_NAME}")
    suspend fun deleteAllRemoteKeys()
}