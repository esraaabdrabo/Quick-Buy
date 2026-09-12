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
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.route.quickbuy.R

@Composable
fun PasswordField(
    state: TextFieldState,
    visibilityState: MutableState<Boolean>,
    hint: String,
    supportingText: String?
) {
    SecureTextField(
        state = state,
        textStyle = MaterialTheme.typography.bodySmall.copy(color = colorResource(R.color.light_grey)),
        shape = RoundedCornerShape(16.dp),
        supportingText = {
            supportingText?.let {
                Text(it, color = MaterialTheme.colorScheme.error)
            }
        },

        textObfuscationMode = if (visibilityState.value) {
            TextObfuscationMode.Companion.Visible
        } else {
            TextObfuscationMode.Companion.Hidden
        },
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Companion.Password,
            imeAction = ImeAction.Companion.Done
        ),
        placeholder = {
            Text(
                hint,
                style =
                    MaterialTheme.typography.bodySmall.copy(
                        color = colorResource(R.color.light_grey)
                    ),
            )
        },
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