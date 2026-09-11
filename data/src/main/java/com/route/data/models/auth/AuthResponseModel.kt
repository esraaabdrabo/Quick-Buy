package com.route.data.models.auth

data class AuthResponseModel(
    val message: String,
    val token: String,
    val user: UserModel,
)
