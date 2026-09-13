package com.route.quickbuy.features.auth.states

sealed interface SignUpEvents {
    data object NavigateToHome : SignUpEvents
    data class SignUpFailed(val message: String) : SignUpEvents
}
