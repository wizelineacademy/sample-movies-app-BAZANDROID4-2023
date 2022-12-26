package com.wizeline.coroutinesexercises.ui.search

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.ContentAlpha
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Clear
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import com.wizeline.coroutinesexercises.R
import com.wizeline.coroutinesexercises.ui.theme.MoviesTheme

@Composable
fun SearchAppBar(
    query: String,
    onQueryChanged: (String) -> Unit,
    onSubmit: (String) -> Unit,
    onClear: () -> Unit,
    navigateUp: (() -> Unit)?
) {
    TopAppBar(
        navigationIcon = {
            if (navigateUp != null) {
                IconButton(onClick = navigateUp) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "navigate up"
                    )
                }
            }
        },
        title = {
            TextField(
                value = query,
                onValueChange = onQueryChanged,
                modifier = Modifier.fillMaxWidth(),
                placeholder = {
                    Text(
                        text = stringResource(id = R.string.search_placeholder),
                        color = MaterialTheme.colors.onPrimary.copy(alpha = ContentAlpha.medium),
                    )
                },
                singleLine = true,
                trailingIcon = {
                    if (query.isNotEmpty()) {
                        IconButton(onClick = onClear) {
                            Icon(
                                imageVector = Icons.Default.Clear,
                                contentDescription = "Clear",
                                tint = MaterialTheme.colors.onPrimary,
                            )
                        }
                    }
                },
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
                keyboardActions = KeyboardActions(
                    onSearch = {
                        onSubmit(query)
                    },
                ),
                colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = Color.Transparent,
                    cursorColor = MaterialTheme.colors.onPrimary.copy(alpha = ContentAlpha.medium),
                )
            )
        }
    )
}

@Preview
@Composable
private fun SearchAppBarPreview() {
    MoviesTheme {
        SearchAppBar(
            query = "Custom query",
            onQueryChanged = {},
            onSubmit = {},
            onClear = {},
            navigateUp = {}
        )
    }
}