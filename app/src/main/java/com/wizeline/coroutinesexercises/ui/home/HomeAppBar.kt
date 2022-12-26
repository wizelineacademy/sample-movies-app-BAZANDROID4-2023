package com.wizeline.coroutinesexercises.ui.home

import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.wizeline.coroutinesexercises.R
import com.wizeline.coroutinesexercises.ui.theme.MoviesTheme

@Composable
fun HomeAppBar(
    onSearchClick: () -> Unit,
) {
    TopAppBar(
        title = { Text(text = stringResource(id = R.string.app_name)) },
        actions = {
            IconButton(onClick = onSearchClick) {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = "Search Icon",
                )
            }
        }
    )
}

@Preview
@Composable
private fun HomeAppBarPreview() {
    MoviesTheme {
        HomeAppBar(onSearchClick = {})
    }
}