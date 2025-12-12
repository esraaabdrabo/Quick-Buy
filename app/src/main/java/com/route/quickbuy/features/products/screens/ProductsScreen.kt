package com.route.quickbuy.features.products.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.route.domain.entities.ProductEntity
import com.route.quickbuy.core.ShoppingCartHeaderIcon
import com.route.quickbuy.core.fields.SearchField
import com.route.quickbuy.features.products.composables.ProductCard
import com.route.quickbuy.features.products.states.ProductsViewModel


@Composable
fun ProductsScreen(productsVM: ProductsViewModel = viewModel()) {

    val products: LazyPagingItems<ProductEntity> =
        productsVM.productsFlow.collectAsLazyPagingItems()
    var searchValue by remember { mutableStateOf("") }

    Column(
        verticalArrangement = Arrangement.Top,
        modifier = Modifier.padding(horizontal = 16.dp)
    )
    {
        Row {
            SearchField(
                onSearchChange = {
                    searchValue = it
                },
                value = searchValue
            )
            ShoppingCartHeaderIcon()
        }
        LazyVerticalGrid(
            modifier = Modifier
                .padding(vertical = 24.dp)
                .weight(1f),
            columns = GridCells.Adaptive(minSize = 150.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            when (products.loadState.refresh) {
                is LoadState.Error -> {
                    item {
                        Text(
                            "Error"
                        )
                    }
                }

                is LoadState.Loading -> {
                    item {
                        CircularProgressIndicator()
                    }
                }

                is LoadState.NotLoading -> items(
                    products.itemCount,
                ) { index ->
                    ProductCard(
                        products[index]!!
                    )
                }
            }

        }
    }
}


@Preview(showBackground = true)
@Composable
fun ProductsScreenPreview() {
    ProductsScreen()
}

