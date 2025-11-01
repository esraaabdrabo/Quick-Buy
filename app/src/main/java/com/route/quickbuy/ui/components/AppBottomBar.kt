package com.route.quickbuy.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.route.quickbuy.ui.theme.primary

@Composable
fun AppBottomBar(selectedItem: BottomBarItems, onClick: (id: BottomBarItems) -> Unit) {
    Row(
        modifier = Modifier.Companion
            .fillMaxWidth()
            .height(80.dp)
            .border(
                width = 1.dp,
                color = primary,
                shape = RoundedCornerShape(
                    200.dp
                )
            )
            .clip(
                RoundedCornerShape(
                    topEnd =
                        15.dp, topStart = 15.dp
                ),
            )
            .background(primary),
        verticalAlignment = Alignment.Companion.CenterVertically,
        horizontalArrangement = Arrangement.SpaceEvenly,
    ) {
        BottomBarItems.entries.forEach { item ->
            BottomBarItem(
                isSelected = selectedItem == item,
                item = item,
                onClick = {
                    onClick(item)
                }
            )
        }
    }
}