package com.example.wallpaperapp.data.service

import com.example.wallpaperapp.BuildConfig
import com.example.wallpaperapp.data.model.UnsplashResponse
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface WallpaperService {

    @Headers("Authorization: Client-ID ${BuildConfig.UNSPLASH_ACCESS_KEY}")
    @GET("photos")
    suspend fun getWallpapers(
        @Query("page") page: Int,
        @Query("per_page") perPage: Int
    ): List<UnsplashResponse>
}