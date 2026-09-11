package com.route.quickbuy.features.products.composables

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.route.quickbuy.R
import com.route.quickbuy.core.buttons.AppIconBTN

@Composable
fun AddToCartBTN() {
    AppIconBTN(
        onClick = {
            //TODO: -Add to cart
        },
        imageVector = Icons.Filled.Add,
        contentDescription = stringResource(R.string.add_to_cart_icon)
    )

}