package com.route.quickbuy.core

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.route.quickbuy.CartDestination
import com.route.quickbuy.R
import com.route.quickbuy.navController

@Composable
fun ShoppingCartHeaderIcon() {
    val navController = navController.current
    IconButton(
        onClick = {
            navController.navigate(CartDestination)
        },
        content = {
            Image(
                painterResource(R.drawable.ic_shopping_cart),
                contentDescription =
                    stringResource(R.string.shopping_cart_icon),
                modifier = Modifier.size(32.dp)
            )
        }
    )
}