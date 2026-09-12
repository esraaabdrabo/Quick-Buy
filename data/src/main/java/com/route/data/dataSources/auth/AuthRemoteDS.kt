package com.route.data.dataSources.auth

import com.route.data.models.auth.AuthResponseModel
import com.route.data.models.auth.SignInRequestModel
import com.route.data.models.auth.SignUpRequestModel
import com.route.data.services.AuthServices
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
abstract class AuthRemoteDataSource {
    abstract suspend fun signIn(request: SignInRequestModel): AuthResponseModel
    abstract suspend fun signUp(request: SignUpRequestModel): AuthResponseModel
    abstract suspend fun refreshToken(token: String): String?
}

class AuthRemoteDataSourceImpl @Inject constructor(
    private val authServices: AuthServices
) : AuthRemoteDataSource() {

    override suspend fun signIn(request: SignInRequestModel): AuthResponseModel {
        return authServices.signIn(request)
    }

    override suspend fun signUp(request: SignUpRequestModel): AuthResponseModel {
        return authServices.signUp(request)
    }

    override suspend fun refreshToken(token: String): String? {
        return authServices.refreshToken(token)
    }
}
