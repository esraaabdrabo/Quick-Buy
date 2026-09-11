package com.route.quickbuy.core

import androidx.compose.foundation.Image
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.res.painterResource
import com.route.quickbuy.R

@Composable
fun ShoppingCartHeaderIcon() {
    IconButton(
        onClick = {
            //TODO: -open shopping cart screen
        },
        content = {
            Image(
                painterResource(R.drawable.ic_shopping_cart),
                contentDescription =
                    "Shopping card icon",
                Modifier.Companion.scale(2.5f)
            )
        }
    )
}