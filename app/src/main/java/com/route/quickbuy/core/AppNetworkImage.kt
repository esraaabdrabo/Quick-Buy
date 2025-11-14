package com.route.quickbuy.core

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ErrorOutline
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import coil3.compose.SubcomposeAsyncImage
import com.route.quickbuy.ui.theme.error

@Composable
fun AppNetworkImage(
    url: String,
    modifier: Modifier = Modifier.Companion,
    contentDescription: String = "Image",
    scale: ContentScale = ContentScale.Companion.FillBounds
) {
    SubcomposeAsyncImage(
        modifier = modifier,
        model = url,
        contentDescription = contentDescription,
        loading = {
            CircularProgressIndicator(modifier = Modifier.Companion.align(Alignment.Companion.Center))
        },
        error = {
            Icon(
                Icons.Filled.ErrorOutline,
                contentDescription = "Error in loading the image",
                tint = error,
            )
        },
        contentScale = scale
    )
}