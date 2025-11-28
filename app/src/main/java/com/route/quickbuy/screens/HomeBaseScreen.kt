package com.route.quickbuy.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.route.quickbuy.R
import com.route.quickbuy.screens.products.ProductsScreen
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
                        actions = {
                            IconButton(
                                onClick = {
                                    //TODO: -open shopping cart screen
                                },
                                content = {
                                    Image(
                                        painterResource(R.drawable.ic_shopping_cart),
                                        contentDescription =
                                            "Shopping card icon",
                                        Modifier.scale(2.5f)
                                    )
                                }
                            )
                        },
                        title = {
                            Column {
                                Image(
                                    modifier = Modifier
                                        .scale(2.5f)
                                        .padding(bottom = 8.dp),
                                    painter = painterResource(
                                        R.drawable.ic_primary_logo
                                    ),
                                    contentDescription = "logo"
                                )
                                TextField(
                                    textStyle = MaterialTheme.typography.titleMedium.copy(color = MaterialTheme.colorScheme.primary),
                                    leadingIcon = {
                                        Icon(
                                            Icons.Filled.Search,
                                            contentDescription = "search icon",
                                            tint =
                                                MaterialTheme.colorScheme.primary,
                                            modifier = Modifier.size(24.dp)
                                        )
                                    },
                                    value = "what do you search for ?",

                                    onValueChange = {
                                        //TODO: -implement search functionality according to the selected tab
                                    },
                                    colors = TextFieldDefaults.colors(
                                        unfocusedContainerColor = Color.Transparent,
                                        focusedContainerColor = Color.Transparent,
                                        errorContainerColor = Color.Transparent,
                                        disabledContainerColor = Color.Transparent,
                                        focusedIndicatorColor = Color.Transparent,
                                        unfocusedIndicatorColor = Color.Transparent,

                                        ),
                                    modifier = Modifier
                                        .border(
                                            1.dp, MaterialTheme.colorScheme.primary,
                                            shape = RoundedCornerShape(
                                                50.dp
                                            ),
                                        )
                                        .padding(horizontal = 24.dp)

                                )
                            }

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
        //TODO: -why this is not lifting the body up? it's behind the body
        Box(modifier = Modifier.height(innerPadding.calculateBottomPadding()))
    }
}