package com.route.data.repo.auth

import com.route.data.dataSources.auth.AuthRemoteDataSource
import com.route.data.models.auth.AuthResponseModel
import com.route.domain.entities.Auth.SignInRequestBodyEntity
import com.route.domain.entities.Auth.SignUpRequestBodyEntity
import com.route.domain.entities.Auth.UserEntity
import com.route.domain.repos.auth.AuthRepo
import javax.inject.Inject

class AuthRepoImpl @Inject constructor(
    private val remoteDataSource: AuthRemoteDataSource
) : AuthRepo() {

    override suspend fun signIn(request: SignInRequestBodyEntity): UserEntity {

        //todo check status code -> handle response according to the status code
        val requestModel = AuthParser.toModel(request)
        val response: AuthResponseModel

        try {
            response = remoteDataSource.signIn(requestModel)
            val entity = AuthParser.toEntity(response)
            return entity
        } catch (e: Exception) {
            throw e
        }

    }

    override suspend fun signUp(request: SignUpRequestBodyEntity): UserEntity {
        val requestModel = AuthParser.toModel(request)
        val response: AuthResponseModel = remoteDataSource.signUp(requestModel)
        return AuthParser.toEntity(response)
    }
}
