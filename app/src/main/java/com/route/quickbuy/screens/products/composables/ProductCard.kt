package com.route.quickbuy.screens.products.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.StarRate
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.route.domain.entities.ProductEntity
import com.route.domain.entities.productList
import com.route.quickbuy.ProductDetailDestination
import com.route.quickbuy.R
import com.route.quickbuy.core.AppNetworkImage
import com.route.quickbuy.navController
import com.route.quickbuy.ui.theme.deepIndigo
import com.route.quickbuy.ui.theme.lightBlue

@Composable
fun ProductCard(product: ProductEntity, modifier: Modifier = Modifier.Companion) {
    val navController = navController.current
    Card(
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.onPrimary
        ),
        modifier = modifier
            .border(2.dp, lightBlue, RoundedCornerShape(15.dp))
            .clickable(onClick = {
                navController.navigate(ProductDetailDestination)
            })

    ) {
        Box(
            contentAlignment = Alignment.Companion.TopEnd
        ) {
            if (product.imageUrl != null)
                AppNetworkImage(
                    product.imageUrl!!,
                    modifier = Modifier
                        .height(150.dp)
                        .fillMaxWidth(),
                    contentDescription = "Product Image"
                )
            Box(
                modifier = Modifier.Companion
                    .padding(8.dp)
                    .shadow(
                        1.dp,
                        ambientColor = Color.Companion.Black.copy(alpha = .2f),
                        shape = CircleShape,

                        )
                    .background(MaterialTheme.colorScheme.onPrimary)
            ) {

                Image(
                    painterResource(R.drawable.heart), contentDescription = "",
                    modifier = Modifier.Companion
                        .padding(6.dp)
                        .clickable(onClick = {
                            //TODO: -Add to favorite
                        }),
                    colorFilter = ColorFilter.Companion.tint(MaterialTheme.colorScheme.primary)

                )
            }

        }
        Column(modifier = Modifier.Companion.padding(8.dp)) {
            Text(
                text = product.name, maxLines = 2, minLines = 2,
                overflow = TextOverflow.Companion.Ellipsis,
                style = MaterialTheme.typography.bodyLarge.copy(
                    color = deepIndigo
                )
            )
            Row(
                verticalAlignment = Alignment.Companion.CenterVertically,

                modifier = Modifier.Companion
                    .padding(vertical = 8.dp)
                    .fillMaxWidth()
            ) {
                Text(
                    text = "EGP ${product.discountedPrice}",
                    maxLines = 1,
                    overflow = TextOverflow.Companion.Ellipsis,
                    style = MaterialTheme.typography.bodyLarge.copy(
                        color = deepIndigo
                    )
                )
                Spacer(modifier = Modifier.Companion.width(16.dp))
                Text(
                    text = "${product.originalPrice} EGP",
                    maxLines = 1,
                    overflow = TextOverflow.Companion.Ellipsis,
                    style = MaterialTheme.typography.bodySmall.copy(
                        color = lightBlue,
                        textDecoration = TextDecoration.Companion.LineThrough
                    )

                )
            }
            Row(
                verticalAlignment = Alignment.Companion.CenterVertically,
                modifier = Modifier.Companion
                    .padding(bottom = 13.dp)
                    .fillMaxWidth()
            ) {
                Text(
                    text = "Review (${product.rating ?: 0}) ",
                    maxLines = 1,
                    overflow = TextOverflow.Companion.Ellipsis,
                    style = MaterialTheme.typography.bodyLarge.copy(
                        color = MaterialTheme.colorScheme.secondary
                    )
                )
                Icon(
                    imageVector = Icons.Filled.StarRate,
                    contentDescription = "Rate icon",
                    tint = Color(0xFFFDD835)
                )
                Spacer(modifier = Modifier.Companion.weight(1f))
                AddToCartBTN()
            }

        }
    }
}

@Preview
@Composable
fun ProductCardPreview() {
    ProductCard(productList.first())
}

