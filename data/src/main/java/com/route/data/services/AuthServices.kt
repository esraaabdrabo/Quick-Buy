package com.route.data.services

import com.route.data.models.auth.AuthResponseModel
import com.route.data.models.auth.SignInRequestModel
import com.route.data.models.auth.SignUpRequestModel
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthServices {
    @POST("/api/v1/auth/signin")
    suspend fun signIn(@Body body: SignInRequestModel): AuthResponseModel

    @POST("/api/v1/auth/signup")
    suspend fun signUp(@Body body: SignUpRequestModel): AuthResponseModel

    @POST("/api/v1/auth/refresh")
    suspend fun refreshToken(@Body body: String): String?
}
