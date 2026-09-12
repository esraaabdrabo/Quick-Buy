package com.route.quickbuy.core.validation

enum class FieldType {
    Email,
    UserName,
    Password
}

enum class InputValidationResult {
    Valid,
    FieldIsRequired,
    InvalidFieldFormat,
    NumbersOnlyAllowed,
    PasswordTooShort,
    PasswordMissingRequirements
}

class InputsValidators {

    private val emailRegex = Regex("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$")
    private val userNameRegex = Regex("^[A-Za-z0-9_]{3,20}$")
    private val minPasswordLength = 8

    private fun checkRequiredResult(value: String?, isRequired: Boolean): InputValidationResult? {
        if (value.isNullOrEmpty()) {
            return if (isRequired) InputValidationResult.FieldIsRequired else InputValidationResult.Valid
        }
        return null
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
}

