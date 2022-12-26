package com.wizeline.coroutinesexercises.ui.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.wizeline.coroutinesexercises.ui.components.DataSection
import com.wizeline.coroutinesexercises.ui.theme.Dimens
import com.wizeline.coroutinesexercises.ui.theme.MoviesTheme
import com.wizeline.coroutinesexercises.utils.GenresFakes

@Composable
fun HomeScreen(uiState: HomeUiState) {
    DataSection(isLoading = uiState.isLoading, errorMessage = uiState.errorMessage) {
        LazyColumn(
            contentPadding = PaddingValues(Dimens.standard),
            verticalArrangement = Arrangement.spacedBy(Dimens.standard + Dimens.standard + Dimens.standard)
        ) {
            items(uiState.genreSections, key = { it.genre.id }) {
                GenreSection(
                    genre = it.genre,
                    movies = it.movies,
                    onMovieClick = {}
                )
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
private fun HomeScreenPreview() {
    MoviesTheme {
        HomeScreen(HomeUiState(genreSections = GenresFakes.genreAndMovies))
    }
}