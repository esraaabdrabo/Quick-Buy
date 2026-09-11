package com.route.quickbuy.features.auth.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import com.route.quickbuy.R

@Composable
fun SignUpScreen(navController: NavHostController) {
    val emailState = rememberTextFieldState();// todo move to vm
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
            Text(stringResource(R.string.welcome_back_to_route))
            Text(stringResource(R.string.please_sign_in_with_your_mail))
            Text(stringResource(R.string.user_name))
            TextField(
                state = emailState,
                label = { stringResource(R.string.enter_your_email_address) }
            )

        }
    )

}

@Preview
@Composable
fun SignUpScreen() {

}