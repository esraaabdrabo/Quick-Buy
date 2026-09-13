package com.route.quickbuy.features.profile.states

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.route.data.core.session.SessionManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val sessionManager: SessionManager
) : ViewModel() {

    fun logout() {
        viewModelScope.launch {
            sessionManager.notifyLoggedOut()
        }
    }
}
