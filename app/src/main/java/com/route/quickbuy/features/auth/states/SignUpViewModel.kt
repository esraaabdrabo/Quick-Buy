package com.route.quickbuy.features.auth.states

import androidx.lifecycle.ViewModel
import com.route.quickbuy.core.validation.FieldType
import com.route.quickbuy.core.validation.InputValidationResult
import com.route.quickbuy.core.validation.InputsValidators
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val validators: InputsValidators
) : ViewModel() {
    private val _errorsState: MutableStateFlow<Map<FieldType, InputValidationResult>> =
        MutableStateFlow(emptyMap())

    val errorsState: StateFlow<Map<FieldType, InputValidationResult>> = _errorsState.asStateFlow()

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
        //call use case
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
