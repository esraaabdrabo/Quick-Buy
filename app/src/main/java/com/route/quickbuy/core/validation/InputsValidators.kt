package com.route.quickbuy.core.validation

import javax.inject.Inject

enum class FieldType {
    FullName,
    MobileNumber,
    Email,
    UserName,
    Password,
    ConfirmPassword,
}

enum class InputValidationResult {
    Valid,
    FieldIsRequired,
    InvalidFieldFormat,
    NumbersOnlyAllowed,
    PasswordTooShort,
    PasswordMissingRequirements,
    ConfirmPasswordDoesNotMatch,
}

class InputsValidators @Inject constructor() {

    private val emailRegex = Regex("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$")
    private val userNameRegex = Regex("^[A-Za-z0-9_]{3,20}$")
    private val minPasswordLength = 8

    private fun checkRequiredResult(value: String?, isRequired: Boolean): InputValidationResult? {
        if (value.isNullOrEmpty()) {
            return if (isRequired) InputValidationResult.FieldIsRequired else InputValidationResult.Valid
        }
        return null
    }

    /**
     * Checks only presence, not format/strength — for contexts like login where
     * the field was already validated against whatever rules applied at creation
     * time, or fields like Full Name / Mobile Number that just need a value.
     */
    fun validateRequired(value: String?, isRequired: Boolean = true): InputValidationResult {
        checkRequiredResult(value, isRequired)?.let { return it }
        return InputValidationResult.Valid
    }

    fun validateEmail(email: String?, isRequired: Boolean = true): InputValidationResult {
        val trimmed = email?.trim()

        checkRequiredResult(trimmed, isRequired)?.let { return it }

        if (!emailRegex.matches(trimmed!!)) {
            return InputValidationResult.InvalidFieldFormat
        }

        return InputValidationResult.Valid
    }

    fun validateUserName(userName: String?, isRequired: Boolean = true): InputValidationResult {
        val trimmed = userName?.trim()

        checkRequiredResult(trimmed, isRequired)?.let { return it }

        if (trimmed!!.all { it.isDigit() }) {
            return InputValidationResult.NumbersOnlyAllowed
        }

        if (!userNameRegex.matches(trimmed)) {
            return InputValidationResult.InvalidFieldFormat
        }

        return InputValidationResult.Valid
    }

    fun validatePassword(password: String?, isRequired: Boolean = true): InputValidationResult {
        checkRequiredResult(password, isRequired)?.let { return it }

        if (password!!.length < minPasswordLength) {
            return InputValidationResult.PasswordTooShort
        }

        val hasLetter = password.any { it.isLetter() }
        val hasDigit = password.any { it.isDigit() }
        if (!hasLetter || !hasDigit) {
            return InputValidationResult.PasswordMissingRequirements
        }

        return InputValidationResult.Valid
    }

    fun validateConfirmPassword(
        password: String?,
        confirmPassword: String?,
        isRequired: Boolean = true
    ): InputValidationResult {
        checkRequiredResult(confirmPassword, isRequired)?.let { return it }

        if (confirmPassword != password) {
            return InputValidationResult.ConfirmPasswordDoesNotMatch
        }

        return InputValidationResult.Valid
    }
}
