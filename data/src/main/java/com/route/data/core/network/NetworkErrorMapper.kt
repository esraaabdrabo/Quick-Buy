package com.route.data.core.network

import ApiErrorResponse
import com.google.gson.Gson
import com.route.domain.core.AppError
import okhttp3.ResponseBody
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

                    val errorBody: ResponseBody? = e.response()?.errorBody();

                    val apiMessage = try {
                        errorBody?.string()?.let { body ->
                            Gson().fromJson(body, ApiErrorResponse::class.java)?.errors?.msg
                        }
                    } catch (ex: Exception) {
                        null
                    } ?: e.message() ?: "Something went wrong"
                    AppError.Api(code = code, apiMessage = apiMessage)
                }
            }

            is SocketTimeoutException -> AppError.Timeout

            is IOException -> AppError.NoConnection

            else -> AppError.Unknown(e)
        }
    }
}
