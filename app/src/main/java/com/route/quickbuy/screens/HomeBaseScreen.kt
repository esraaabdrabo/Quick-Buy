package com.route.quickbuy.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.route.domain.entities.productList
import com.route.quickbuy.screens.products.ProductsScreen
import com.route.quickbuy.ui.components.AppBottomBar
import com.route.quickbuy.ui.components.BottomBarItems

@Composable
fun HomeBaseScreen() {
    var selectedItem: BottomBarItems by rememberSaveable {
        mutableStateOf(
            BottomBarItems.Home
        )
    }
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            AppBottomBar(selectedItem = selectedItem) { item ->
                selectedItem = item
            }
        }
    ) { innerPadding ->
        Body(selectedItem = selectedItem)
    }
}

@Composable
fun Body(
    modifier: Modifier = Modifier,
    selectedItem: BottomBarItems,

    ) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    )
    {
        when (selectedItem) {
            BottomBarItems.Home -> {
                ProductsScreen(products = productList)
            }

            BottomBarItems.Category -> {
                Text(
                    text = "Categories Screen",
                    modifier = modifier
                )
            }

            BottomBarItems.WishList -> {
                Text(
                    text = "WishList Screen",
                    modifier = modifier
                )

            }

            BottomBarItems.Profile -> {
                Text(
                    text = "Profile Screen",
                    modifier = modifier
                )

            }
        }
    }
}