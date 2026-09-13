package com.route.data.core.network

import com.route.domain.core.AppError
import retrofit2.HttpException
import java.io.IOException
import java.net.SocketTimeoutException

object NetworkErrorMapper {

    fun map(e: Throwable): AppError {
        return when (e) {
            is HttpException -> {
                val code = e.code()
                if (code == 401) {
                    AppError.SessionExpired
                } else {
                    val apiMessage = e.response()?.errorBody()?.string()?.takeIf { it.isNotBlank() }
                        ?: e.message()
                    AppError.Api(code = code, apiMessage = apiMessage)
                }
            }

            is SocketTimeoutException -> AppError.Timeout

            is IOException -> AppError.NoConnection

            else -> AppError.Unknown(e)
        }
    }
}
