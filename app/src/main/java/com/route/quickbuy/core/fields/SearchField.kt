package com.route.quickbuy.core.fields

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun SearchField(onSearchChange: (String) -> Unit, value: String) {
    TextField(
        textStyle = MaterialTheme.typography.titleMedium.copy(color = MaterialTheme.colorScheme.primary),
        leadingIcon = {
            Icon(
                Icons.Filled.Search,
                contentDescription = "search icon",
                tint =
                    MaterialTheme.colorScheme.primary,
                modifier = Modifier.Companion.size(24.dp)
            )
        },
        value = value,
        onValueChange = onSearchChange,
        maxLines = 1,
        colors = TextFieldDefaults.colors(
            unfocusedContainerColor = Color.Companion.Transparent,
            focusedContainerColor = Color.Companion.Transparent,
            errorContainerColor = Color.Companion.Transparent,
            disabledContainerColor = Color.Companion.Transparent,
            focusedIndicatorColor = Color.Companion.Transparent,
            unfocusedIndicatorColor = Color.Companion.Transparent,

            ),
        modifier = Modifier.Companion
            .border(
                1.dp, MaterialTheme.colorScheme.primary,
                shape = RoundedCornerShape(
                    50.dp
                ),
            )
            .padding(horizontal = 24.dp)

    )
}