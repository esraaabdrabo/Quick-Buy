package com.route.domain.usecases.auth

import com.route.domain.core.AppResult
import com.route.domain.entities.Auth.SignInRequestBodyEntity
import com.route.domain.entities.Auth.SignUpRequestBodyEntity
import com.route.domain.entities.Auth.UserEntity
import com.route.domain.repos.auth.AuthRepo
import javax.inject.Inject

class SignInUseCase @Inject constructor(private val repo: AuthRepo) {
    suspend fun invoke(request: SignInRequestBodyEntity): AppResult<UserEntity> {
        return repo.signIn(request)
    }
}

class SignUpUseCase @Inject constructor(private val repo: AuthRepo) {
    suspend fun invoke(request: SignUpRequestBodyEntity): UserEntity {
        return repo.signUp(request)
    }
}

class LogoutUseCase @Inject constructor(private val repo: AuthRepo) {
    suspend fun invoke() {
        repo.logout()
    }
}
