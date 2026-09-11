package com.route.quickbuy.features.products.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material.icons.filled.StarRate
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableIntState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.route.quickbuy.R
import com.route.quickbuy.core.AppNetworkImage
import com.route.quickbuy.core.ShoppingCartHeaderIcon
import com.route.quickbuy.features.products.states.details.DataState
import com.route.quickbuy.features.products.states.details.ErrorState
import com.route.quickbuy.features.products.states.details.LoadingState
import com.route.quickbuy.features.products.states.details.ProductDetailsViewModel
import com.route.quickbuy.navController
import com.route.quickbuy.ui.theme.royal_blue_30
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductDetailScreen(id: String) {
    val scrollState = rememberScrollState()

    val viewModel = viewModel<ProductDetailsViewModel>()

    val state by viewModel.state

    LaunchedEffect(id) {
        if (state is DataState) return@LaunchedEffect
        viewModel.getProductDetails(id)
    }
    val colorSchema = MaterialTheme.colorScheme
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                navigationIcon = {
                    val navController = navController.current
                    IconButton(
                        onClick = {
                            navController.popBackStack()
                        }
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = stringResource(R.string.back),
                            tint = colorSchema.primary

                        )

                    }
                },
                title = {
                    Text(
                        stringResource(R.string.product_details),

                        style =
                            MaterialTheme.typography.titleMedium.copy(
                                color = colorSchema.primary
                            )
                    )
                },
                actions = {
                    ShoppingCartHeaderIcon()
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = colorSchema.onPrimary
                )
            )
        },
        content = { paddingValues ->
            Box(
                modifier = Modifier
                    .padding(paddingValues)
                    .fillMaxSize()
                    .verticalScroll(scrollState),
            ) {
                when (state) {
                    is LoadingState -> {
                        Box(
                            modifier = Modifier
                                .fillMaxSize(),
                            contentAlignment = Alignment.Center
                        ) {
                            CircularProgressIndicator()
                        }
                    }

                    is ErrorState -> {
                        Box(
                            modifier = Modifier
                                .fillMaxSize(),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(state.toString())
                        }
                    }

                    is DataState -> {
                        val productDetails = (state as DataState).data
                        val images: List<String?>? = productDetails.images
                        val pagerState = remember {
                            PagerState(currentPage = 0, pageCount = { images?.size ?: 0 })
                        }


                        val orderCount = remember {
                            mutableIntStateOf(1)
                        }

                        Column(
                            Modifier
                                .padding(16.dp)
                                .fillMaxSize(),
                            verticalArrangement = Arrangement.Top,
                        ) {

                            Box(contentAlignment = Alignment.BottomCenter) {
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
                                Row() {
                                    for (i in 0 until (images?.size ?: 0)) {
                                        val isSelected = i == pagerState.currentPage
                                        val color =
                                            if (isSelected) colorSchema.primary else colorSchema.secondary.copy(
                                                alpha = .2f
                                            )
                                        val coroutineScope = rememberCoroutineScope()

                                        Box(
                                            modifier = Modifier
                                                .padding(5.dp)
                                                .size(15.dp)
                                                .clip(CircleShape)
                                                .background(color)
                                                .clickable(onClick = {
                                                    coroutineScope.launch {
                                                        pagerState.animateScrollToPage(i)
                                                    }
                                                })
                                        )
                                    }

                                }
                            }

                            Row(modifier = Modifier.padding(vertical = 16.dp)) {
                                if (productDetails.title != null)
                                    Text(
                                        productDetails.title!!,
                                        modifier = Modifier.weight(1f),
                                        style = MaterialTheme.typography.titleMedium.copy(
                                            color = colorSchema.secondary
                                        )

                                    )
                                Text(
                                    stringResource(R.string.price_original, productDetails.price.toString()),
                                    style = MaterialTheme.typography.titleMedium.copy(
                                        color = colorSchema.secondary
                                    )
                                )
                            }

                            Row(verticalAlignment = Alignment.CenterVertically) {
                                if (productDetails.sold != null)
                                    Text(
                                        stringResource(R.string.sold_count, "${productDetails.sold!!}".take(4)),
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
                                    contentDescription = stringResource(R.string.rate_icon),
                                    tint = Color(0xFFFDD835)
                                )
                                Text(
                                    stringResource(
                                        R.string.rating_summary,
                                        productDetails.ratingsAverage ?: 0,
                                        productDetails.ratingsQuantity ?: 0
                                    )
                                )

                                Spacer(modifier = Modifier.weight(1f))

                                OrderCount(
                                    count = orderCount,
                                    quantity = productDetails.quantity ?: 0
                                )
                            }

                            if (productDetails.description != null) {

                                Text(
                                    stringResource(R.string.description_label), modifier = Modifier.padding(
                                        top = 16.dp,
                                        bottom = 8.dp
                                    ),
                                    style = MaterialTheme.typography.titleMedium.copy(
                                        color = colorSchema.secondary
                                    )
                                )
                                ProductDescription(productDetails.description!!)
                            }
                        }
                    }
                }
            }
        }

    )

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
            Icons.Filled.Remove, contentDescription = stringResource(R.string.remove),
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
            Icons.Filled.Add, contentDescription = stringResource(R.string.add),
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

@Composable
fun ProductDescription(description: String) {
    var allowedMaxLines by remember {
        mutableIntStateOf(2)
    }
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(
                onClick = {
                    allowedMaxLines = if (allowedMaxLines == 2)
                        Int.MAX_VALUE
                    else
                        2

                }
            )) {
        Text(
            description,
            style = MaterialTheme.typography.bodyLarge.copy(
                color = MaterialTheme.colorScheme.secondary.copy(alpha = .6f)
            ),
            maxLines = allowedMaxLines,
            modifier = Modifier.weight(1f)
        )
        if (allowedMaxLines != Int.MAX_VALUE)
            Text(
                stringResource(R.string.read_more),
                style = MaterialTheme.typography.bodyLarge.copy(
                    color = MaterialTheme.colorScheme.secondary
                ),
            )
    }
}