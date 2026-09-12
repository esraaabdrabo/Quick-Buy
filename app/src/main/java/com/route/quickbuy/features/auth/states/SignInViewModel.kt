package com.route.quickbuy.features.auth.states

import androidx.lifecycle.ViewModel
import com.route.domain.usecases.auth.SignInUseCase
import com.route.quickbuy.core.validation.FieldType
import com.route.quickbuy.core.validation.InputValidationResult
import com.route.quickbuy.core.validation.InputsValidators
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(
    private val signIn: SignInUseCase,
    private val validators: InputsValidators
) : ViewModel() {

    private val _errorsState: MutableStateFlow<Map<FieldType, InputValidationResult>> =
        MutableStateFlow(emptyMap())
    val errorsState: StateFlow<Map<FieldType, InputValidationResult>> = _errorsState.asStateFlow()

    fun login(userName: String, password: String) {
        val validationResult: Map<FieldType, InputValidationResult> = mapOf(
            FieldType.UserName to validators.validateUserName(userName),
            FieldType.Password to validators.validatePassword(password),
        )
        val canSubmit = validationResult.values.all { it == InputValidationResult.Valid }
        if (!canSubmit) {
            _errorsState.value = validationResult
            return
        }
        // TODO: call signIn use case
    }
}
