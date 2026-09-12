package com.route.data.core.network

import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

class AccessTokenInterception : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val token: String = "" //todo from secure storage
        val request: Request =
            chain.request().newBuilder().addHeader("Authorization", token).build()
        return chain.proceed(request = request)
    }

}