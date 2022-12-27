package com.wizeline.coroutinesexercises.ui.home

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.wizeline.coroutinesexercises.ui.theme.MoviesTheme
import com.wizeline.coroutinesexercises.utils.fakes.GenresFakes

@Composable
fun HomeScreen(
    uiState: HomeUiState,
    navigateToSearch: () -> Unit
) {
    Scaffold(
        topBar = { HomeAppBar(onSearchClick = navigateToSearch) }
    ) { innerPadding ->
        with(uiState) {
            HomeContent(
                modifier = Modifier.padding(innerPadding),
                isLoading = isLoading,
                errorMessage = errorMessage,
                genreSections = genreSections
            )
        }
    }
}

@Preview(showSystemUi = true)
@Composable
private fun HomeScreenPreview() {
    MoviesTheme {
        HomeScreen(
            uiState = HomeUiState(genreSections = GenresFakes.genreAndMovies),
            navigateToSearch = {}
        )
    }
}