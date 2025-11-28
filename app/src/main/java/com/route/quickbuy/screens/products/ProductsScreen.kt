package com.route.quickbuy.screens.products

import ProductsRepoImpl
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.route.data.dataSources.products.ProductsRemoteDataSourceImpl
import com.route.domain.entities.ProductEntity
import com.route.domain.usecases.products.GetProductsUseCase
import com.route.quickbuy.screens.products.composables.ProductCard

data class ProductsScreenState(
    val value: List<ProductEntity>?,
    val errorMessage: String?,
    val isLoading: Boolean
)

@Composable
fun ProductsScreen() {
    val states: MutableState<ProductsScreenState> = remember {
        mutableStateOf(
            ProductsScreenState(
                isLoading = true,
                value = null,
                errorMessage = null
            )
        )

    }
    val getProductsUseCase = GetProductsUseCase(
        ProductsRepoImpl(
            ProductsRemoteDataSourceImpl()
        )
    )
    LaunchedEffect(1) {

        states.value = states.value.copy(isLoading = true)

        val data = getProductsUseCase.invoke(1)
        //hide loading
        states.value = states.value.copy(isLoading = false, value = data)
        // show error or data
        print(states.value)
    }



    Column(modifier = Modifier.padding(horizontal = 16.dp))
    {
        if (states.value.isLoading)

            CircularProgressIndicator()
        else
            LazyVerticalGrid(
                modifier = Modifier.padding(vertical = 24.dp),
                columns = GridCells.Adaptive(minSize = 150.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                val products = states.value.value ?: emptyList()

                items(
                    products.size,
                    key = { index ->
                        products[index].id
                    }

                ) {

                        index ->
                    ProductCard(
                        products[index], Modifier.animateItem(
                            fadeInSpec = tween(
                                durationMillis = 500
                            )
                        )
                    )
                }
            }
    }
}


@Preview(showBackground = true)
@Composable
fun ProductsScreenPreview() {
    ProductsScreen()
}