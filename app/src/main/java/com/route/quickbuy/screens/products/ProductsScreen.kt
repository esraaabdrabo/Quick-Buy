package com.route.quickbuy.screens.products

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.StarRate
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.route.domain.entities.ProductEntity
import com.route.quickbuy.R
import com.route.quickbuy.ui.theme.deepIndigo
import com.route.quickbuy.ui.theme.lightBlue
import com.route.quickbuy.ui.theme.primary

@Composable
fun ProductsScreen() {
    val products: List<ProductEntity> = emptyList()
    ProductCard()
}

@Composable
fun ProductCard() {
    Card() {
        Image(
            modifier = Modifier
                .fillMaxWidth(),
            painter = painterResource(R.drawable.ad1),
            contentDescription = "Product image",
            contentScale = ContentScale.FillWidth
        )
        Column(modifier = Modifier.padding(   8.dp)){
        Text(
            text = "Nike Air Jordon Nike shoes flexible for wo Nike Air Jordon Nike shoes flexible for wo",
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
            style = MaterialTheme.typography.bodyLarge.copy(
                color = deepIndigo
            )
        )
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(vertical = 8.dp)
        ) {
            Text(
                text = "EGP 1,100",
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                style = MaterialTheme.typography.bodyLarge.copy(
                    color = deepIndigo
                )
            )
            Spacer(modifier = Modifier.width(16.dp))
            Text(
                text = "1500 EGP",
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                style = MaterialTheme.typography.bodySmall.copy(
                    color = lightBlue,
                    textDecoration = TextDecoration.LineThrough
                )

            )
        }
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(bottom = 13.dp)) {
            Text(
                text = "Review (4.5) ",
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                style = MaterialTheme.typography.bodyLarge.copy(
                    color = MaterialTheme.colorScheme.secondary
                )
            )
            Icon(imageVector = Icons.Filled.StarRate, contentDescription = "Rate icon")
            Spacer(modifier = Modifier)
            IconButton(onClick = {},
            modifier = Modifier.clip(CircleShape).background(
                MaterialTheme.colorScheme.primary
            )
                ) {
                Icon(
                    imageVector = Icons.Filled.Add,
                    contentDescription = "Add to cart icon",
                    tint = MaterialTheme.colorScheme.onPrimary
                )
            }
            }

        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ProductsScreen()
}