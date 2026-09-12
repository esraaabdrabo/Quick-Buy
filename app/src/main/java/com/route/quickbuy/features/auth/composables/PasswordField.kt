package com.route.quickbuy.features.auth.composables

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.foundation.text.input.TextObfuscationMode
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SecureTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import com.route.quickbuy.R

@Composable
fun PasswordField(
    state: TextFieldState,
    visibilityState: MutableState<Boolean>,
    label: String,
    supportingText: String?
) {
    SecureTextField(
        state = state,
        label = {
            Text(
                label,
                style = MaterialTheme.typography.bodySmall.copy(
                    color = Color(0xFF000000).copy(alpha = .7f)
                )
            )
        },
        shape = RoundedCornerShape(percent = 50),
        supportingText = { if (supportingText == null) null else Text(supportingText) },
        textObfuscationMode = if (visibilityState.value) {
            TextObfuscationMode.Companion.Visible
        } else {
            TextObfuscationMode.Companion.Hidden
        },
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Companion.Password,
            imeAction = ImeAction.Companion.Done
        ),
        trailingIcon = {
            IconButton(onClick = { visibilityState.value = !visibilityState.value }) {
                Icon(
                    imageVector = if (visibilityState.value) Icons.Default.VisibilityOff else Icons.Default.Visibility,
                    contentDescription = stringResource(R.string.password_visibility_icon)
                )
            }
        }
    )
}