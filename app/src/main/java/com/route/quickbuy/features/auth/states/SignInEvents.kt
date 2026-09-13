package com.route.quickbuy.features.auth.states

sealed interface SignInEvents {
    data object NavigateToHome : SignInEvents
    data object SignInFailed : SignInEvents
}

