package com.route.data.models.auth

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class UserModel(
    val email: String,
    val name: String,
    val role: String,
) : Parcelable
