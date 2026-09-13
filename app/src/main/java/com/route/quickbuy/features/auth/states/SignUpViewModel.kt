package com.route.quickbuy.features.auth.states

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.route.domain.core.AppResult
import com.route.domain.entities.Auth.SignUpRequestBodyEntity
import com.route.domain.usecases.auth.SignUpUseCase
import com.route.quickbuy.core.validation.FieldType
import com.route.quickbuy.core.validation.InputValidationResult
import com.route.quickbuy.core.validation.InputsValidators
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val signUp: SignUpUseCase,
    private val validators: InputsValidators
) : ViewModel() {
    private val _errorsState: MutableStateFlow<Map<FieldType, InputValidationResult>> =
        MutableStateFlow(emptyMap())

    val errorsState: StateFlow<Map<FieldType, InputValidationResult>> = _errorsState.asStateFlow()

    private val loadingState = MutableStateFlow(false)
    val isLoading = loadingState.asStateFlow()

    private val _events: Channel<SignUpEvents> = Channel(Channel.BUFFERED)
    val events = _events.receiveAsFlow()

    fun signUp(
        fullName: String,
        mobileNumber: String,
        email: String,
        password: String,
        confirmPassword: String,
    ) {
        val validationResult: Map<FieldType, InputValidationResult> = validateInputs(
            fullName,
            mobileNumber,
            email,
            password,
            confirmPassword,
        )
        val canSubmit: Boolean =
            validationResult.values.all { it == InputValidationResult.Valid }
        if (!canSubmit) {
            _errorsState.value = validationResult
            return
        }
        _errorsState.value = emptyMap()
        viewModelScope.launch {
            loadingState.value = true

            when (val result = signUp.invoke(
                SignUpRequestBodyEntity(
                    email = email,
                    name = fullName,
                    password = password,
                    confirmPassword = confirmPassword,
                    phone = mobileNumber,
                )
            )) {
                is AppResult.Success -> _events.send(SignUpEvents.NavigateToHome)
                is AppResult.Failure -> _events.send(
                    SignUpEvents.SignUpFailed(message = result.error.message)
                )
            }

            loadingState.value = false
        }
    }

    fun validateInputs(
        fullName: String,
        mobileNumber: String,
        email: String,
        password: String,
        confirmPassword: String,
    ): Map<FieldType, InputValidationResult> {
        return mapOf(
            FieldType.FullName to validators.validateRequired(fullName),
            FieldType.MobileNumber to validators.validateRequired(mobileNumber),
            FieldType.Email to validators.validateEmail(email),
            FieldType.Password to validators.validatePassword(password),
            FieldType.ConfirmPassword to validators.validateConfirmPassword(
                password, confirmPassword = confirmPassword,
            ),
        )
    }
}
