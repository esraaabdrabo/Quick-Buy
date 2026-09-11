package com.route.quickbuy.features

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.route.quickbuy.R
import com.route.quickbuy.features.products.screens.ProductsScreen
import com.route.quickbuy.ui.components.AppBottomBar
import com.route.quickbuy.ui.components.BottomBarItems

@Preview
@Composable
fun HomeBaseScreenPreview() {
    HomeBaseScreen()
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeBaseScreen() {
    var selectedItem: BottomBarItems by rememberSaveable {
        mutableStateOf(
            BottomBarItems.Home
        )
    }
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            when (selectedItem) {
                BottomBarItems.Profile -> {}
                else -> {
                    TopAppBar(
                        colors = TopAppBarColors(
                            subtitleContentColor = MaterialTheme.colorScheme.onPrimary,
                            titleContentColor = MaterialTheme.colorScheme.onSecondary,
                            navigationIconContentColor = MaterialTheme.colorScheme.onSecondary,
                            actionIconContentColor = MaterialTheme.colorScheme.onSecondary,
                            scrolledContainerColor = MaterialTheme.colorScheme.onPrimary,
                            containerColor = MaterialTheme.colorScheme.onPrimary,
                        ),
                        title = {
                            Image(
                                modifier = Modifier
                                    .padding(start = 24.dp)
                                    .scale(2.5f)
                                    .padding(bottom = 8.dp),
                                painter = painterResource(
                                    R.drawable.ic_primary_logo
                                ),
                                contentDescription = stringResource(R.string.app_logo)
                            )
                        },
                    )
                }
            }
        },
        bottomBar = {
            AppBottomBar(selectedItem = selectedItem) { item ->
                selectedItem = item
            }
        }
    ) { innerPadding ->

        Body(selectedItem = selectedItem, innerPadding = innerPadding)

    }
}

@Composable
fun Body(
    modifier: Modifier = Modifier,
    selectedItem: BottomBarItems,
    innerPadding: PaddingValues

) {

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(
                top = innerPadding.calculateTopPadding(),
                bottom = innerPadding.calculateBottomPadding()
            )
            .background(MaterialTheme.colorScheme.onPrimary),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    )
    {

        when (selectedItem) {
            BottomBarItems.Home -> {
                ProductsScreen()
            }

            BottomBarItems.Category -> {
                Text(
                    text = stringResource(R.string.categories_screen),
                    modifier = modifier
                )
            }

            BottomBarItems.WishList -> {
                Text(
                    text = stringResource(R.string.wishlist_screen),
                    modifier = modifier
                )

            }

            BottomBarItems.Profile -> {
                Text(
                    text = stringResource(R.string.profile_screen),
                    modifier = modifier
                )

            }
        }
        //TODO: -why this is not lifting the body up? it's behind the body
        Box(modifier = Modifier.height(innerPadding.calculateBottomPadding()))
    }
}