package com.example.wallpaperapp.domain.error

interface ErrorHandler {
    fun getError(throwable: Throwable?): ErrorEntity
}