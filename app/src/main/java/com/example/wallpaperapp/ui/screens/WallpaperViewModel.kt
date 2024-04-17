package com.example.wallpaperapp.ui.screens

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import com.example.wallpaperapp.database.entity.WallpaperEntity
import com.example.wallpaperapp.domain.WallpapersRepository
import com.example.wallpaperapp.domain.error.ErrorHandler
import com.example.wallpaperapp.utils.CacheManager
import com.example.wallpaperapp.utils.getErrorMessage
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import java.net.URL
import javax.inject.Inject

@HiltViewModel
class WallpaperViewModel @Inject constructor(
    private val wallpapersRepository: WallpapersRepository,
    private val appContext: Context,
    private val errorHandler: ErrorHandler
) : ViewModel() {


    private val _errorMessage = MutableStateFlow("")
    val errorMessage: StateFlow<String> = _errorMessage.asStateFlow()

    fun getWallpapers(): Flow<PagingData<WallpaperEntity>> {
        return wallpapersRepository.getAllWallpapers()
            .catch {
                val errorEntity = errorHandler.getError(it)
                _errorMessage.value = getErrorMessage(errorEntity)
            }.flowOn(Dispatchers.Main)
    }

    fun downloadMyWallpaper(url: String?, onBitmapLoaded: (Bitmap?) -> Unit) {
        viewModelScope.launch(Dispatchers.IO) {
            url?.let {
                // First check if the image is in cache
                val cachedBitmap = CacheManager.getImageFromCache(appContext, it)
                val bitmap = cachedBitmap ?: try {
                    val inputStream = URL(it).openStream()
                    BitmapFactory.decodeStream(inputStream)
                } catch (e: Exception) {
                    e.printStackTrace()
                    null
                }
                onBitmapLoaded.invoke(bitmap)
                bitmap?.let {
                    CacheManager.saveImageToCache(appContext, url, bitmap)
                }
            }
        }
    }
}