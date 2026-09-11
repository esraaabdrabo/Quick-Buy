package com.route.domain.entities.Auth

data class AuthResponseEntity(
    val message: String,
    val token: String,
    val user: UserEntity
)
