package com.example.wallpaperapp.data.mapper

import com.example.wallpaperapp.data.model.UnsplashResponse
import com.example.wallpaperapp.database.entity.WallpaperEntity
import com.example.wallpaperapp.domain.model.Urls
import com.example.wallpaperapp.domain.model.User
import com.example.wallpaperapp.domain.model.UserLinks


fun List<UnsplashResponse>.toDomain(): List<WallpaperEntity> {
    return this.map {
        it.toDomain()
    }
}

fun UnsplashResponse.toDomain() = WallpaperEntity(
    id.toString(), urls?.toDomain(), likes, user?.toDomain()
)

fun UnsplashResponse.UrlsResponse.toDomain() = Urls(
    regular, small
)

fun UnsplashResponse.UserResponse.toDomain() = User(
    links.toDomain(), username
)

fun UnsplashResponse.UserResponse.UserLinksResponse.toDomain() = UserLinks(
    html
)