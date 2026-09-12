package com.route.quickbuy.core.validation

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.route.quickbuy.R


@Composable
fun InputValidationResult.getMessage(): String = when (this) {
    InputValidationResult.FieldIsRequired -> stringResource(R.string.field_is_required)
    InputValidationResult.InvalidFieldFormat -> stringResource(R.string.invalid_field_format)
    InputValidationResult.NumbersOnlyAllowed -> stringResource(R.string.numbers_only_allowed)
    InputValidationResult.PasswordTooShort -> stringResource(R.string.password_too_short)
    InputValidationResult.PasswordMissingRequirements -> stringResource(R.string.password_missing_requirements)
    InputValidationResult.Valid -> ""
}