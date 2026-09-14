package com.route.quickbuy.features.products.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemKey
import com.route.domain.entities.ProductEntity
import com.route.quickbuy.LocalConnectivityObserver
import com.route.quickbuy.R
import com.route.quickbuy.core.ShoppingCartHeaderIcon
import com.route.quickbuy.core.fields.SearchField
import com.route.quickbuy.features.products.composables.ProductCard
import com.route.quickbuy.features.products.states.ProductsViewModel
import com.route.quickbuy.features.products.states.details.DataState
import com.route.quickbuy.features.products.states.details.ErrorState
import com.route.quickbuy.features.products.states.details.LoadingState

@Composable
fun ProductsScreen(productsVM: ProductsViewModel = hiltViewModel()) {
    val hasConnection by LocalConnectivityObserver.current.isConnected.collectAsState()


    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .padding(horizontal = 16.dp)
            .fillMaxSize()
    )
    {
        if (!hasConnection) {
            Text(stringResource(R.string.no_internet_connection))
            return@Column
        }

        val products: LazyPagingItems<ProductEntity> =
            productsVM.productsFlow.collectAsLazyPagingItems()

        val state = productsVM.state

        when (state.value) {
            is LoadingState -> {
                CircularProgressIndicator()
            }

            is ErrorState -> {
                Text(
                    stringResource(R.string.something_went_wrong),
                    modifier = Modifier.padding(16.dp),
                    style = MaterialTheme.typography.bodyLarge.copy(
                        color = MaterialTheme.colorScheme.primary
                    )

                )
                Text((state.value as ErrorState<List<ProductEntity>>).error)
            }

            is DataState -> {
                val noData: Boolean = (state.value as DataState<List<ProductEntity>>).data.isEmpty()
                if (noData) {
                    Text(stringResource(R.string.no_data_found))
                    return@Column
                }
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    SearchField(
                        modifier = Modifier.weight(1f),
                        onSearchChange = productsVM::onSearchQueryChanged,
                        value = productsVM.searchValue
                    )
                    ShoppingCartHeaderIcon()
                }

                val isSearching = productsVM.isSearching
                val isDebouncing = productsVM.isDebouncing
                val filteredProducts: List<ProductEntity> = remember(
                    productsVM.debouncedQuery,
                    products.itemSnapshotList
                ) {
                    productsVM.filterProducts(products.itemSnapshotList.filterNotNull())
                }

                val gridState = rememberLazyGridState()
                val focusManager = LocalFocusManager.current
                val keyboardController = LocalSoftwareKeyboardController.current
                LaunchedEffect(gridState.isScrollInProgress) {
                    if (gridState.isScrollInProgress) {
                        focusManager.clearFocus()
                        keyboardController?.hide()
                    }
                }
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier.weight(1f)
                ) {
                    if (isDebouncing) {
                        CircularProgressIndicator()
                    } else if (isSearching && filteredProducts.isEmpty()) {
                        Text(
                            stringResource(R.string.no_result),
                            textAlign = TextAlign.Center,
                            style = MaterialTheme.typography.bodyLarge.copy(color = MaterialTheme.colorScheme.primary)
                        )

                    } else {
                        LazyVerticalGrid(
                            state = gridState,
                            modifier = Modifier
                                .padding(vertical = 24.dp)

                                .fillMaxSize(),
                            columns = GridCells.Adaptive(minSize = 150.dp),
                            verticalArrangement = Arrangement.spacedBy(16.dp),
                            horizontalArrangement = Arrangement.spacedBy(16.dp)
                        ) {
                            if (isSearching) {
                                items(filteredProducts) { product ->
                                    ProductCard(product = product)
                                }

                            } else {
                                when (products.loadState.refresh) {
                                    is LoadState.Error -> {
                                        item {
                                            Text(
                                                stringResource(R.string.error)
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
                                        key = products.itemKey { it.id }
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

        }
    }
}


@Preview(showBackground = true)
@Composable
fun ProductsScreenPreview() {
    ProductsScreen()
}

