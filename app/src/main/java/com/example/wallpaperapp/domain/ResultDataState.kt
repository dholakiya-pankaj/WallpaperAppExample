package com.example.wallpaperapp.domain

import com.example.wallpaperapp.domain.error.ErrorEntity

sealed class ResultDataState<out R> {

    data class Success<out T>(val data: T) : ResultDataState<T>()

    data class Error(val exception: Exception) : ResultDataState<Nothing>()

    data class ErrorMessage(val errorMessage: String) : ResultDataState<Nothing>()

    data class ErrorResult<T>(val error: ErrorEntity) : ResultDataState<T>()

    object Loading: ResultDataState<Nothing>()
}
