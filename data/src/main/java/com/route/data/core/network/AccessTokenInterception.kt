package com.route.data.core.network

import com.route.data.services.AuthServices
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import javax.inject.Inject

class AccessTokenInterception @Inject constructor(
    private val tokensServices: TokensServices,
    private val authSServices: AuthServices,
) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val token = runBlocking { tokensServices.getAccessToken() }
        if (token == null) {
            // no Logout method in backend
        }
        val request: Request = chain.request().newBuilder()
            .apply { token?.let { addHeader("Authorization", it) } }
            .build()

        return chain.proceed(request = request)
    }

}