package com.route.data.repo.auth

import com.route.data.core.network.NetworkErrorMapper
import com.route.data.dataSources.auth.AuthLocalDataSource
import com.route.data.dataSources.auth.AuthRemoteDataSource
import com.route.data.models.auth.AuthResponseModel
import com.route.domain.core.AppResult
import com.route.domain.entities.Auth.SignInRequestBodyEntity
import com.route.domain.entities.Auth.SignUpRequestBodyEntity
import com.route.domain.entities.Auth.UserEntity
import com.route.domain.repos.auth.AuthRepo
import kotlinx.coroutines.CancellationException
import javax.inject.Inject

class AuthRepoImpl @Inject constructor(
    private val remoteDataSource: AuthRemoteDataSource,
    private val localDataSource: AuthLocalDataSource
) : AuthRepo() {

    override suspend fun signIn(request: SignInRequestBodyEntity): AppResult<UserEntity> {
        val requestModel = AuthParser.toModel(request)

        return try {
            val response: AuthResponseModel = remoteDataSource.signIn(requestModel)
            localDataSource.saveToken(response.token)
            AppResult.Success(AuthParser.toEntity(response))
        } catch (e: CancellationException) {
            throw e
        } catch (e: Exception) {
            AppResult.Failure(NetworkErrorMapper.map(e))
        }
    }

    override suspend fun signUp(request: SignUpRequestBodyEntity): UserEntity {
        val requestModel = AuthParser.toModel(request)
        val response: AuthResponseModel = remoteDataSource.signUp(requestModel)
        return AuthParser.toEntity(response)
    }

    override suspend fun logout() {
        localDataSource.clearToken()
    }
}
