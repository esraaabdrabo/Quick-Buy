package com.route.quickbuy.features.auth.composables

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.input.TextFieldLineLimits
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

@Composable
fun AuthField(
    state: TextFieldState,
    label: String,
    supportingText: String?,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Companion.Default,
) {
    OutlinedTextField(
        state = state,
        shape = RoundedCornerShape(percent = 50),
        colors = TextFieldDefaults.colors(
            focusedContainerColor = MaterialTheme.colorScheme.onPrimary,
            unfocusedContainerColor = MaterialTheme.colorScheme.onPrimary,
            focusedIndicatorColor = MaterialTheme.colorScheme.onPrimary,
            unfocusedIndicatorColor = MaterialTheme.colorScheme.onPrimary,
            cursorColor = MaterialTheme.colorScheme.onPrimary,
            focusedLabelColor = MaterialTheme.colorScheme.onPrimary,
            errorContainerColor = MaterialTheme.colorScheme.errorContainer,
            disabledIndicatorColor = Color.Companion.Transparent,
            errorIndicatorColor = Color.Companion.Transparent,
        ),
        lineLimits = TextFieldLineLimits.SingleLine,
        keyboardOptions = keyboardOptions,
        label = {
            Text(
                label,
                style = MaterialTheme.typography.bodySmall.copy(
                    color = Color(0xFF000000).copy(alpha = .7f)
                )
            )
        },
        supportingText = { if (supportingText == null) null else Text(supportingText) },
    )
}