package com.route.quickbuy.features.auth.states

import androidx.lifecycle.ViewModel
import com.route.domain.usecases.auth.SignUpUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val signUp: SignUpUseCase
) : ViewModel() {

}
