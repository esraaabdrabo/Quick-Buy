package com.route.quickbuy.core.fields

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import com.route.quickbuy.R

@Composable
fun SearchField(
    onSearchChange: (String) -> Unit,
    value: String,
    modifier: Modifier = Modifier
) {
    val focusManager = LocalFocusManager.current
    val keyboardController = LocalSoftwareKeyboardController.current

    TextField(
        textStyle = MaterialTheme.typography.titleMedium.copy(color = MaterialTheme.colorScheme.primary),
        leadingIcon = {
            Icon(
                Icons.Filled.Search,
                contentDescription = stringResource(R.string.search_icon),
                tint =
                    MaterialTheme.colorScheme.primary,
                modifier = Modifier.Companion.size(24.dp)
            )
        },
        trailingIcon = {
            if (value.isNotEmpty()) {
                IconButton(onClick = {
                    onSearchChange("")
                    keyboardController?.hide()
                    focusManager.clearFocus()
                }) {
                    Icon(
                        Icons.Filled.Clear,
                        contentDescription = "Clear search",
                        tint = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.Companion.size(20.dp)
                    )
                }
            }
        },
        keyboardOptions = KeyboardOptions(
            imeAction = ImeAction.Search
        ),
        keyboardActions = KeyboardActions(
            onSearch = {
                keyboardController?.hide()
                focusManager.clearFocus()
            }
        ),
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
        modifier = modifier
            .fillMaxWidth()
            .border(
                1.dp, MaterialTheme.colorScheme.primary,
                shape = RoundedCornerShape(
                    50.dp
                ),
            )
            .padding(horizontal = 24.dp)

    )
}