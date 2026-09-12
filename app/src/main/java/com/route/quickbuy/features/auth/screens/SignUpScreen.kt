package com.route.quickbuy.features.auth.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.route.quickbuy.R

@Composable
fun SignUpScreen(navController: NavHostController) {
    val emailState = rememberTextFieldState();// todo move to vm
    val passwordState = remember { mutableSetOf<String>() }
    return Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.primary),
        content = {
            Image(
                painter = painterResource(R.drawable.ic_white_logo),
                contentDescription = stringResource(R.string.app_logo)
            )
            Text(
                stringResource(R.string.welcome_back_to_route),
                style = MaterialTheme.typography.labelLarge
            )
            Text(
                stringResource(R.string.please_sign_in_with_your_mail),
                style = MaterialTheme.typography.bodySmall
            )
            Text(
                stringResource(R.string.user_name),
                style = MaterialTheme.typography.labelSmall

            )
            AuthField(
                state = emailState,
                label = stringResource(R.string.email_address),

                )

        }
    )

}

@Preview
@Composable
fun SignUpScreen() {

}

@Composable
fun AuthField(
    state: TextFieldState,
    label: String,
) {
    return TextField(
        modifier = Modifier.background(
            MaterialTheme.colorScheme.primary
        ),
        shape = RoundedCornerShape(percent = 50),

        state = state,
        label = {
            label
        },
        textStyle = MaterialTheme.typography.bodySmall.copy(color = Color(0xFFFF0000).copy(alpha = .7f)),

        )
}

@Composable
fun PasswordField(
    valueState: MutableState<String>,
    visibilityState: MutableState<Boolean>,
) {

    OutlinedTextField(
        value = valueState.value,
        onValueChange = { valueState.value = it },
        label = { Text(stringResource(R.string.enter_your_password)) },
        shape = RoundedCornerShape(12.dp),
        visualTransformation = if (visibilityState.value) {
            VisualTransformation.None
        } else {
            PasswordVisualTransformation()
        },

        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Password,
            imeAction = ImeAction.Done
        ),

        trailingIcon = {
            val icon =
                if (visibilityState.value) Icons.Default.VisibilityOff else Icons.Default.Visibility
            IconButton(onClick = { visibilityState.value = !visibilityState.value }) {
                Icon(imageVector = icon, contentDescription = null)
            }
        }
    )
}

