package com.route.data.repo.auth

import com.route.data.models.auth.AuthResponseModel
import com.route.data.models.auth.SignInRequestModel
import com.route.data.models.auth.SignUpRequestModel
import com.route.domain.entities.Auth.SignInRequestBodyEntity
import com.route.domain.entities.Auth.SignUpRequestBodyEntity
import com.route.domain.entities.Auth.UserEntity

class AuthParser {
    companion object {
        fun toEntity(response: AuthResponseModel): UserEntity {
            val user = response.user;
            return UserEntity(
                email = user.email,
                name = user.name,
                role = user.role,
            )
        }

        fun toModel(request: SignInRequestBodyEntity): SignInRequestModel {
            return SignInRequestModel(
                email = request.email,
                password = request.password
            )
        }

        fun toModel(request: SignUpRequestBodyEntity): SignUpRequestModel {
            return SignUpRequestModel(
                name = request.name,
                email = request.email,
                password = request.password,
                confirmPassword = request.confirmPassword,
                phone = request.phone
            )
        }
    }
}
