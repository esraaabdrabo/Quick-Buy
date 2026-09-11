package com.route.quickbuy.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.route.quickbuy.ui.theme.onPrimary
import com.route.quickbuy.ui.theme.primary

@Composable
fun BottomBarItem(
    isSelected: Boolean, item: BottomBarItems,
    onClick: () -> Unit = {}
) {
    Box(
        modifier = Modifier.Companion
            .size(40.dp)
            .background(
                color = if (isSelected) onPrimary else primary,
                shape = CircleShape,
            )
            .padding(8.dp)
            .clickable(
                onClick = {
                    onClick()
                }
            ),
        contentAlignment = Alignment.Companion.Center

    )
    {
        Icon(
            modifier = Modifier.Companion
                .size(40.dp),
            painter = painterResource(item.iconID),
            contentDescription = stringResource(item.labelResId),
            tint =
                if (isSelected) primary
                else
                    onPrimary
        )
    }
}