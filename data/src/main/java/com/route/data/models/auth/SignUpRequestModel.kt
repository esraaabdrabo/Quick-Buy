package com.route.data.models.auth

import com.google.gson.annotations.SerializedName

data class SignUpRequestModel(
    val name: String,
    val email: String,
    val password: String,
    @SerializedName("rePassword")
    val confirmPassword: String,
    val phone: String
)
