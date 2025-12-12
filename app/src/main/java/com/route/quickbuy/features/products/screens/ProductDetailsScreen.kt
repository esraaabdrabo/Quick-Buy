package com.route.quickbuy.features.products.screens

import ProductsRepoImpl
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material.icons.filled.StarRate
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableIntState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.route.data.dataSources.products.ProductsRemoteDataSourceImpl
import com.route.domain.entities.ProductDetailsEntity
import com.route.domain.usecases.products.GetProductDetailsUseCase
import com.route.quickbuy.core.AppNetworkImage
import com.route.quickbuy.ui.theme.royal_blue_30

open class ProductDetailsState {}

class ProductDetailsLoadingState : ProductDetailsState()
data class ProductDetailsErrorState(val error: String) : ProductDetailsState()
data class ProductDetailsDataState(val product: ProductDetailsEntity) : ProductDetailsState()


@Composable
fun ProductDetailScreen(id: String) {
    Log.e("id", id)
    val getProductDetailsUseCase =
        GetProductDetailsUseCase(ProductsRepoImpl(ProductsRemoteDataSourceImpl()))

    val state = remember { mutableStateOf(ProductDetailsState()) }
    LaunchedEffect(1)
    {
        state.value = ProductDetailsLoadingState()
        try {
            val details = getProductDetailsUseCase.invoke(id)
            state.value = ProductDetailsDataState(details)
        } catch (e: Exception) {
            state.value = ProductDetailsErrorState(e.message ?: "Unknown Error")
        }
    }

    when (state.value) {
        is ProductDetailsLoadingState -> {
            CircularProgressIndicator()
        }

        is ProductDetailsErrorState -> {
            Text(state.value.toString())
        }

        is ProductDetailsDataState -> {
            val productDetails = (state.value as ProductDetailsDataState).product
            val images: List<String?>? = productDetails.images
            val pagerState = remember {
                PagerState(currentPage = 0, pageCount = { images?.size ?: 0 })
            }
            state.value = ProductDetailsDataState(productDetails)

            val orderCount = remember {
                mutableIntStateOf(1)
            }

            Column(Modifier.padding(16.dp)) {
                val colorSchema = MaterialTheme.colorScheme
                HorizontalPager(
                    pagerState,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp)
                        .border(
                            1.dp, royal_blue_30, RoundedCornerShape(
                                15.dp
                            )
                        ),

                    ) { index: Int ->
                    AppNetworkImage(
                        images!![index]!!, modifier = Modifier
                            .fillMaxWidth()
                            .clip(
                                RoundedCornerShape(
                                    15.dp
                                )
                            )
                    )
                }


                Row(modifier = Modifier.padding(vertical = 16.dp)) {
                    if (productDetails.title != null)
                        Text(
                            productDetails.title!!,

                            style = MaterialTheme.typography.titleMedium.copy(
                                color = colorSchema.secondary
                            )

                        )
                    Spacer(modifier = Modifier.weight(1f))
                    Text(
                        productDetails.price.toString() + " EGP",
                        style = MaterialTheme.typography.titleMedium.copy(
                            color = colorSchema.secondary
                        )
                    )
                }

                Row(verticalAlignment = Alignment.CenterVertically) {
                    if (productDetails.sold != null)
                        Text(
                            "${productDetails.sold!!}".take(4).plus(" Sold"),
                            style = MaterialTheme.typography.bodyLarge.copy(
                                color = colorSchema.secondary
                            ),
                            modifier = Modifier
                                .border(
                                    1.dp,
                                    colorSchema.primary.copy(alpha = .3f),
                                    shape = CircleShape
                                )
                                .padding(horizontal = 8.dp, vertical = 6.dp),
                        )

                    Icon(
                        modifier = Modifier.padding(start = 16.dp),
                        imageVector = Icons.Filled.StarRate,
                        contentDescription = "Rate icon",
                        tint = Color(0xFFFDD835)
                    )
                    Text("${productDetails.ratingsAverage ?: 0} (${productDetails.ratingsQuantity ?: 0})")

                    Spacer(modifier = Modifier.weight(1f))

                    OrderCount(count = orderCount, quantity = productDetails.quantity ?: 0)
                }

                // add to cart
                if (productDetails.description != null) {

                    Text(
                        "Description", modifier = Modifier.padding(
                            top = 16.dp,
                            bottom = 8.dp
                        ),
                        style = MaterialTheme.typography.titleMedium.copy(
                            color = colorSchema.secondary
                        )
                    )
                    Text(
                        productDetails.description!!,
                        style = MaterialTheme.typography.bodyLarge.copy(
                            color = colorSchema.secondary.copy(alpha = .6f)
                        )
                    )// need to be read more
                }
            }
        }
    }


}

@Composable
private fun OrderCount(count: MutableIntState, quantity: Int) {
    val colorSchema = MaterialTheme.colorScheme
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .clip(RoundedCornerShape(50.dp))

            .background(
                color = colorSchema.primary,
            )
            .padding(8.dp)


    ) {

        Icon(
            Icons.Filled.Remove, contentDescription = "Remove",
            modifier = Modifier
                .padding(end = 12.dp)
                .border(
                    1.dp, colorSchema.onPrimary, CircleShape
                )
                .clickable(onClick = { if (count.intValue == 0) return@clickable else count.intValue-- }),
            tint = colorSchema.onPrimary
        )

        Text(
            count.intValue.toString(), style = MaterialTheme.typography.titleMedium.copy(
                color = colorSchema.onPrimary
            )
        )
        Icon(
            Icons.Filled.Add, contentDescription = "Add",
            modifier = Modifier
                .padding(start = 12.dp)
                .border(
                    1.dp, colorSchema.onPrimary, CircleShape
                )
                .clickable(onClick = { if (quantity == count.intValue) return@clickable else count.intValue++ }),
            tint = colorSchema.onPrimary
        )

    }
}