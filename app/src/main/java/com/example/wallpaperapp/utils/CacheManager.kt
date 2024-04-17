package com.example.wallpaperapp.utils

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream

object CacheManager {
    private const val CACHE_DIR = "wallpaper_cache"

    fun saveImageToCache(context: Context, url: String, bitmap: Bitmap) {
        val fileName = url.hashCode().toString()
        val cacheDir = File(context.cacheDir, CACHE_DIR)
        if (!cacheDir.exists()) {
            cacheDir.mkdirs()
        }
        val file = File(cacheDir, fileName)
        FileOutputStream(file).use {
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, it)
        }
    }

    fun getImageFromCache(context: Context, url: String): Bitmap? {
        val fileName = url.hashCode().toString()
        val cacheDir = File(context.cacheDir, CACHE_DIR)
        val file = File(cacheDir, fileName)
        if (file.exists()) {
            FileInputStream(file).use {
                return BitmapFactory.decodeStream(it)
            }
        }
        return null
    }
}