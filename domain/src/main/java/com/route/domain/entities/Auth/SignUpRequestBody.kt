package com.route.domain.entities.Auth

data class SignUpRequestBodyEntity(
    val name: String,
    val email: String,
    val password: String,
    val confirmPassword: String,
    val phone: String,
)
