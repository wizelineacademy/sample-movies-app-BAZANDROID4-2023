package com.wizeline.coroutinesexercises.ui.search

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.wizeline.coroutinesexercises.ui.theme.MoviesTheme
import com.wizeline.coroutinesexercises.utils.fakes.MoviesFakes

@Composable
fun SearchScreen(
    uiState: SearchUiState,
    onQueryChanged: (String) -> Unit,
    onClear: () -> Unit,
    navigateUp: () -> Unit,
) {
    Scaffold(
        topBar = {
            SearchAppBar(
                query = uiState.query,
                onQueryChanged = onQueryChanged,
                onSubmit = onQueryChanged,
                onClear = onClear,
                navigateUp = navigateUp
            )
        }
    ) { innerPadding ->
        with(uiState) {
            SearchContent(
                modifier = Modifier.padding(innerPadding),
                isLoading = isLoading,
                errorMessage = errorMessage,
                isEmptyQuery = uiState.query.isEmpty(),
                movies = movies
            )
        }
    }
}

@Preview(showSystemUi = true)
@Composable
private fun SearchScreenPreview() {
    MoviesTheme {
        SearchScreen(
            uiState = SearchUiState(
                query = "Avatar",
                movies = listOf(MoviesFakes.avatar, MoviesFakes.narnia)
            ),
            onQueryChanged = {},
            onClear = {},
            navigateUp = {},
        )
    }
}