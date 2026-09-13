package com.route.quickbuy.features.splash.states

import androidx.lifecycle.ViewModel
import com.route.data.core.network.TokensServices
import com.route.quickbuy.BaseHomeDestination
import com.route.quickbuy.Destination
import com.route.quickbuy.SignInDestination
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class InitializationViewModel @Inject constructor(
    private val tokensServices: TokensServices
) : ViewModel() {
    suspend fun getInitialDestination(): Destination {
        if (tokensServices.isSessionExpired()) {
            val newToken = tokensServices.refreshToken() ?: return SignInDestination;
            return BaseHomeDestination
        } else {
            return BaseHomeDestination

        }
    }
}