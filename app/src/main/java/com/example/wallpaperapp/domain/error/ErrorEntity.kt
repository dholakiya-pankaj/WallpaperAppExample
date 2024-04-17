package com.example.wallpaperapp.domain.error

import com.example.wallpaperapp.data.GeneralErrorHandlerImpl

sealed class ErrorEntity {
    object Network : ErrorEntity()

    object NotFound : ErrorEntity()

    object AccessDenied : ErrorEntity()

    object ServiceUnavailable : ErrorEntity()

    object Authentication : ErrorEntity()

    data class BadRequest(val errorResponse: GeneralErrorHandlerImpl.ErrorResponse) : ErrorEntity()

    object Unknown : ErrorEntity()
}
