package com.route.domain.repos.auth

import com.route.domain.entities.Auth.SignInRequestBodyEntity
import com.route.domain.entities.Auth.SignUpRequestBodyEntity
import com.route.domain.entities.Auth.UserEntity

abstract class AuthRepo {
    abstract suspend fun signIn(request: SignInRequestBodyEntity): UserEntity
    abstract suspend fun signUp(request: SignUpRequestBodyEntity): UserEntity
    abstract suspend fun logout()
}
