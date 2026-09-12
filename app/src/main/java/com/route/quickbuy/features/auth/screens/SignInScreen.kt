package com.route.quickbuy.features.auth.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.route.quickbuy.R
import com.route.quickbuy.SignUpDestination
import com.route.quickbuy.core.buttons.AppRoundedButton
import com.route.quickbuy.core.validation.FieldType
import com.route.quickbuy.core.validation.InputValidationResult
import com.route.quickbuy.core.validation.getMessage
import com.route.quickbuy.features.auth.composables.AuthField
import com.route.quickbuy.features.auth.composables.PasswordField
import com.route.quickbuy.features.auth.states.SignInViewModel

@Composable
fun SignInScreen(
    navController: NavHostController,
    viewModel: SignInViewModel = hiltViewModel()
) {
    val userNameState = rememberTextFieldState()
    val passwordState = rememberTextFieldState()
    val passwordVisibilityState = remember { mutableStateOf(false) }
    val fieldsErrors by viewModel.errorsState.collectAsStateWithLifecycle()

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .imePadding()
            .background(color = MaterialTheme.colorScheme.primary)
            .padding(horizontal = 24.dp, vertical = 16.dp),
    ) {
        Image(
            painter = painterResource(R.drawable.ic_white_logo),
            contentDescription = stringResource(R.string.app_logo)
        )

        Spacer(modifier = Modifier.height(24.dp))

        Text(
            stringResource(R.string.welcome_back_to_route),
            style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold),
            color = MaterialTheme.colorScheme.onPrimary,
        )
        Text(
            stringResource(R.string.please_sign_in_with_your_mail),
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onPrimary,
        )

        Spacer(modifier = Modifier.height(24.dp))

        Text(
            stringResource(R.string.user_name),
            style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.Bold),
            color = MaterialTheme.colorScheme.onPrimary,
            modifier = Modifier.fillMaxWidth(),
        )
        Spacer(modifier = Modifier.height(4.dp))
        AuthField(
            state = userNameState,
            label = stringResource(R.string.enter_your_name),
            supportingText = fieldsErrors[FieldType.UserName]?.takeIf { it != InputValidationResult.Valid }
                ?.getMessage()
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            stringResource(R.string.password),
            style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.Bold),
            color = MaterialTheme.colorScheme.onPrimary,
            modifier = Modifier.fillMaxWidth(),
        )
        Spacer(modifier = Modifier.height(4.dp))
        PasswordField(
            state = passwordState,
            visibilityState = passwordVisibilityState,
            label = stringResource(R.string.enter_your_password),
            supportingText = fieldsErrors[FieldType.Password]?.takeIf { it != InputValidationResult.Valid }
                ?.getMessage()
        )

        Text(
            stringResource(R.string.forgot_password),
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onPrimary,
            textAlign = TextAlign.End,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp)
                .clickable {
                },
        )

        Spacer(modifier = Modifier.height(32.dp))

        AppRoundedButton(
            text = stringResource(R.string.login),
            onClick = {
                viewModel.login(
                    userName = userNameState.text.toString(),
                    password = passwordState.text.toString(),
                )
            },
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            stringResource(R.string.dont_have_an_account_create_account),
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onPrimary,
            modifier = Modifier.clickable {
                navController.navigate(SignUpDestination)
            },
        )
    }
}
