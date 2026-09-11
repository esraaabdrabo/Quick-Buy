package com.route.data.models.auth

data class SignUpRequestModel(
    val name: String,
    val email: String,
    val password: String,
    val confirmPassword: String,
    val phone: String
)
