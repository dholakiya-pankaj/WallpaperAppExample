package com.example.wallpaperapp.utils

import android.content.Context
import android.widget.Toast
import com.example.wallpaperapp.domain.error.ErrorEntity

fun Context.showToast(message: String, length: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(this, message, length).show()
}

fun getErrorMessage(entity: ErrorEntity): String {
    return when (entity) {
        is ErrorEntity.AccessDenied -> "Access Denied."
        is ErrorEntity.Authentication -> "Authentication Error. Please check your credential."
        is ErrorEntity.BadRequest -> "Bad Request."
        is ErrorEntity.Network -> "Network error. Please check your internet connection."
        is ErrorEntity.NotFound -> "User not found."
        is ErrorEntity.ServiceUnavailable -> "Service is temporary unavailable."
        is ErrorEntity.Unknown -> "Unknown Error"
    }
}