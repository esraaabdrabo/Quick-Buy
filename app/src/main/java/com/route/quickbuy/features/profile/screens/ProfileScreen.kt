package com.route.quickbuy.features.profile.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.route.quickbuy.core.buttons.AppRoundedButton
import com.route.quickbuy.features.profile.states.ProfileViewModel

@Preview
@Composable
fun ProfileScreenPreview(
) {
    ProfileScreen()
}

@Composable
fun ProfileScreen(
    viewModel: ProfileViewModel = hiltViewModel()
) {
    Column(
        modifier = Modifier.background(MaterialTheme.colorScheme.primary)
        .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        AppRoundedButton(
            onClick = {
                viewModel.logout()
            },
            text = "Logout"
        )

    }
}