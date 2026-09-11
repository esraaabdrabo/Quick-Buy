package com.route.quickbuy.core.buttons

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.route.quickbuy.R


@Composable
fun AppIconBTN(
    onClick: () -> Unit,
    size: Dp = 26.dp,
    background: Color = MaterialTheme.colorScheme.primary,
    imageVector: ImageVector,
    contentDescription: String = stringResource(R.string.icon_button),
    iconTint: Color = MaterialTheme.colorScheme.onPrimary
) {
    IconButton(
        onClick = onClick,
        modifier = Modifier
            .clip(CircleShape)
            .size(size)
            .background(
                background
            )
    ) {
        Icon(
            imageVector = imageVector,
            contentDescription = contentDescription,
            tint = iconTint
        )
    }
}