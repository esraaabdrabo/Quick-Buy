package com.route.data.dataSources.auth

import android.content.SharedPreferences
import androidx.core.content.edit
import com.route.data.di.EncryptedPrefs
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
abstract class AuthLocalDataSource {
    abstract suspend fun saveToken(token: String)
    abstract suspend fun getToken(): String?
    abstract suspend fun clearToken()

}

class AuthLocalDataSourceImpl @Inject constructor(
    @EncryptedPrefs private val encryptedPrefs: SharedPreferences
) : AuthLocalDataSource() {

    override suspend fun saveToken(token: String) = withContext(Dispatchers.IO) {
        encryptedPrefs.edit { putString(KEY_ACCESS_TOKEN, token) }
    }

    override suspend fun getToken(): String? = withContext(Dispatchers.IO) {
        encryptedPrefs.getString(KEY_ACCESS_TOKEN, null)
    }

    override suspend fun clearToken() = withContext(Dispatchers.IO) {
        encryptedPrefs.edit { remove(KEY_ACCESS_TOKEN) }
    }

    private companion object {
        const val KEY_ACCESS_TOKEN = "access_token"
    }
}
