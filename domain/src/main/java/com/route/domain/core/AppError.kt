package com.route.domain.core

sealed class AppError(val message: String) {
    object NoConnection : AppError("No internet connection")
    object Timeout : AppError("Request timed out")
    object SessionExpired : AppError("Session expired, please sign in again")
    data class Api(val code: Int, val apiMessage: String) : AppError(apiMessage)
    data class Unknown(val cause: Throwable) : AppError(cause.message ?: "Something went wrong")
}
