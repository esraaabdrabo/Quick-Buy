package com.route.data.core.network

import android.util.Base64
import com.route.data.core.network.Services.DateServices
import com.route.data.core.session.SessionManager
import com.route.data.dataSources.auth.AuthLocalDataSource
import com.route.data.dataSources.auth.AuthRemoteDataSource
import org.json.JSONObject
import javax.inject.Inject


class TokensServices @Inject constructor(
    private val dateServices: DateServices,
    private val authRemoteDataSource: AuthRemoteDataSource,
    private val authLocalDataSource: AuthLocalDataSource,
    private val sessionManager: SessionManager
) {

    //{
//  "id": "6aa5810d4da3e6001246c606",
//  "name": "Ahmed Abd Al-Muti",
//  "role": "user",
//  "iat": 1789231373,
//  "exp": 1797007373
//}
    suspend fun getAccessToken(): String? {
        if (isSessionExpired()) {
            return refreshToken()
        }
        return authLocalDataSource.getToken()
    }

    suspend fun isSessionExpired(): Boolean {
        val token = authLocalDataSource.getToken() ?: return true
        val json = decodeJwtPayload(token) ?: return true
        val expDate = dateServices.convertDateFromTimeStamp((json["exp"] as Number).toLong())
        return expDate.isBefore(dateServices.getCurrentDate())
    }

    suspend fun refreshToken(): String? {
        val refreshToken = authLocalDataSource.getToken() ?: run {
            sessionManager.notifySessionExpired()
            return null
        }
        var newToken: String? = null
        try {
            // The backend has no refresh API, this will return same token (Esraa: review postman again)
            newToken = authRemoteDataSource.refreshToken(refreshToken)
            if (newToken != null) {
                authLocalDataSource.saveToken(newToken)
                return newToken
            }
            sessionManager.notifySessionExpired()
            return null
        } catch (e: Exception) {
            sessionManager.notifySessionExpired()
        }
        return null
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