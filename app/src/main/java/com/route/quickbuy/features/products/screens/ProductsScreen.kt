package com.route.quickbuy.features.products.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.route.domain.entities.ProductEntity
import com.route.quickbuy.connectivityObserver
import com.route.quickbuy.core.ShoppingCartHeaderIcon
import com.route.quickbuy.core.fields.SearchField
import com.route.quickbuy.features.products.composables.ProductCard
import com.route.quickbuy.features.products.states.ProductsViewModel
import com.route.quickbuy.features.products.states.details.DataState
import com.route.quickbuy.features.products.states.details.ErrorState
import com.route.quickbuy.features.products.states.details.LoadingState


@Composable
fun ProductsScreen(productsVM: ProductsViewModel = viewModel()) {


    val hasConnection = connectivityObserver.current.isConnected.value


    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .padding(horizontal = 16.dp)
            .fillMaxSize()
    )
    {
        if (!hasConnection) {
            Text("No Internet Connection")
            return@Column
        }

        val products: LazyPagingItems<ProductEntity> =
            productsVM.productsFlow.collectAsLazyPagingItems()
        var searchValue by remember { mutableStateOf("") }

        val state = productsVM.state

        when (state.value) {
            is LoadingState -> {
                CircularProgressIndicator()
            }

            is ErrorState -> {
                Text(
                    "Something went wrong", modifier = Modifier.padding(16.dp),
                    style = MaterialTheme.typography.bodyLarge.copy(
                        color = MaterialTheme.colorScheme.primary
                    )

                )
                Text((state.value as ErrorState).error)
            }

            is DataState -> {
                val noData: Boolean = (state.value as DataState<List<ProductEntity>>).data.isEmpty()
                if (noData) {
                    Text("No Data Found")
                    return@Column
                }
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
                        .weight(1f)
                        .fillMaxSize(),
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
    }
}


@Preview(showBackground = true)
@Composable
fun ProductsScreenPreview() {
    ProductsScreen()
}

