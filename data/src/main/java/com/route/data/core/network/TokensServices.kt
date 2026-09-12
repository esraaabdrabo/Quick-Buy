package com.route.data.core.network

import android.util.Base64
import com.route.data.core.network.Services.DateServices
import com.route.data.dataSources.auth.AuthLocalDataSource
import com.route.data.dataSources.auth.AuthRemoteDataSource
import org.json.JSONObject
import javax.inject.Inject


class TokensServices @Inject constructor(
    private val dateServices: DateServices,
    private val authRemoteDataSource: AuthRemoteDataSource,
    private val authLocalDataSource: AuthLocalDataSource
) {

    //{
//  "id": "6aa5810d4da3e6001246c606",
//  "name": "Ahmed Abd Al-Muti",
//  "role": "user",
//  "iat": 1789231373,
//  "exp": 1797007373
//}
    suspend fun getValidAccessToken(): String? {
        val token = authLocalDataSource.getToken() ?: return null
        val json = decodeJwtPayload(token) ?: return null
        val expDate = dateServices.convertDateFromTimeStamp((json["exp"] as Number).toLong())

        if (expDate.isBefore(dateServices.getCurrentDate())) {
            return try {
                // The backend has no refresh API, this will return same token (Esraa: review postman again)
                val newToken = authRemoteDataSource.refreshToken(token)
                if (newToken != null) authLocalDataSource.saveToken(newToken)
                newToken
            } catch (e: Exception) {
                null // caller should treat null as "logout"
            }
        }

        return token
    }


    fun decodeJwtPayload(token: String): JSONObject? {
        return try {
            val payload = token.split(".")[1]

            val decoded = Base64.decode(
                payload,
                Base64.URL_SAFE or Base64.NO_WRAP
            )

            JSONObject(String(decoded, Charsets.UTF_8))
        } catch (e: Exception) {
            null
        }
    }
}