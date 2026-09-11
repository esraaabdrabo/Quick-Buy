package com.route.quickbuy.features.auth.states

import androidx.lifecycle.ViewModel
import com.route.domain.usecases.auth.SignInUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(
    private val signIn: SignInUseCase
) : ViewModel() {


}
