package com.route.quickbuy.features.cart.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.route.quickbuy.R
import com.route.quickbuy.navController

// Placeholder cart screen: just enough to navigate to until the real cart
// (state, items, checkout) is built.
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CartScreen() {
    val colorScheme = MaterialTheme.colorScheme
    val navController = navController.current

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = stringResource(R.string.back),
                            tint = colorScheme.primary
                        )
                    }
                },
                title = {
                    Text(
                        "Shopping Cart",
                        style = MaterialTheme.typography.titleMedium.copy(color = colorScheme.primary)
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = colorScheme.onPrimary
                )
            )
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text(
                "Your cart is empty",
                style = MaterialTheme.typography.bodyLarge.copy(color = colorScheme.secondary)
            )
        }
    }
}
