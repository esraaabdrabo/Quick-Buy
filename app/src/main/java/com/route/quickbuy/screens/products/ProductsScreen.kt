package com.route.quickbuy.screens.products

import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.route.domain.entities.ProductEntity
import com.route.domain.entities.productList
import com.route.quickbuy.screens.products.composables.ProductCard

@Composable
fun ProductsScreen(products: List<ProductEntity>) {
    Column(modifier = Modifier.padding(horizontal = 16.dp))
    {
        LazyVerticalGrid(
            modifier = Modifier.padding(vertical = 24.dp),
            columns = GridCells.Adaptive(minSize = 150.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {

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
    ProductsScreen(
        productList
    )
}