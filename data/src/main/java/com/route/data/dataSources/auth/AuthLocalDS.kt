package com.route.data.dataSources.auth

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
abstract class AuthLocalDataSource {
    abstract suspend fun saveToken(token: String)
    abstract suspend fun getToken(): String?

}

// wanna use secure storage here
class AuthLocalDataSourceImpl @Inject constructor() : AuthLocalDataSource() {


    override suspend fun saveToken(token: String) {
    }

    override suspend fun getToken(): String? {
        return "";
    }

}
