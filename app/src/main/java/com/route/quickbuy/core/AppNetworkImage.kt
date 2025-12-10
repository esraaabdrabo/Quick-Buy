package com.route.quickbuy.core

import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ErrorOutline
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil3.compose.SubcomposeAsyncImage
import com.route.quickbuy.ui.theme.error

@Composable
fun AppNetworkImage(
    url: String,
    modifier: Modifier = Modifier,
    contentDescription: String = "Image",
    scale: ContentScale = ContentScale.FillBounds
) {
    SubcomposeAsyncImage(
        modifier = modifier,
        model = url,
        contentDescription = contentDescription,
        loading = {
            CircularProgressIndicator(
                modifier = Modifier
                    .width(24.dp)
                    .align(Alignment.Center)
            )
        },
        error = {
            Icon(
                Icons.Filled.ErrorOutline,
                modifier = Modifier
                    .width(24.dp),
                contentDescription = "Error in loading the image",
                tint = error,
            )
        },
        contentScale = scale
    )
}