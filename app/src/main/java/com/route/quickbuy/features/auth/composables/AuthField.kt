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
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import com.route.quickbuy.R

@Composable
fun AuthField(
    state: TextFieldState,
    hint: String,
    supportingText: String?,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Companion.Default,
) {
    OutlinedTextField(
        state = state,
        shape = RoundedCornerShape(16.dp),
        colors = TextFieldDefaults.colors(
            focusedContainerColor = MaterialTheme.colorScheme.onPrimary,
            unfocusedContainerColor = MaterialTheme.colorScheme.onPrimary,
            focusedIndicatorColor = MaterialTheme.colorScheme.onPrimary,
            unfocusedIndicatorColor = MaterialTheme.colorScheme.onPrimary,
            cursorColor = MaterialTheme.colorScheme.primary,
            focusedLabelColor = MaterialTheme.colorScheme.onPrimary,
            errorContainerColor = MaterialTheme.colorScheme.errorContainer,
            disabledIndicatorColor = Color.Companion.Transparent,
            errorIndicatorColor = Color.Companion.Transparent,
        ),
        textStyle = MaterialTheme.typography.bodySmall.copy(
            color = colorResource(R.color.light_grey)
        ),
        lineLimits = TextFieldLineLimits.SingleLine,
        keyboardOptions = keyboardOptions,
        placeholder = {
            Text(
                hint,
                style =
                    MaterialTheme.typography.bodySmall.copy(
                        color = colorResource(R.color.light_grey)
                    ),
            )
        },
        supportingText = {
            if (supportingText == null) null else Text(
                supportingText,
                color = MaterialTheme.colorScheme.error,
            )
        },
    )
}