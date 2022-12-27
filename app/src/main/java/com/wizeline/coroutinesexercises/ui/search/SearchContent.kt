package com.wizeline.coroutinesexercises.ui.search

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.wizeline.coroutinesexercises.R
import com.wizeline.coroutinesexercises.domain.entities.Movie
import com.wizeline.coroutinesexercises.ui.components.Center
import com.wizeline.coroutinesexercises.ui.components.DataSection
import com.wizeline.coroutinesexercises.ui.theme.Dimens
import com.wizeline.coroutinesexercises.ui.theme.MoviesTheme
import com.wizeline.coroutinesexercises.utils.fakes.MoviesFakes

@Composable
fun SearchContent(
    modifier: Modifier = Modifier,
    isLoading: Boolean,
    errorMessage: String?,
    isEmptyQuery: Boolean,
    movies: List<Movie>,
) {
    DataSection(
        modifier = modifier,
        isLoading = isLoading,
        errorMessage = errorMessage
    ) {
        if (!isLoading && movies.isEmpty()) {
            Center {
                Text(
                    text = stringResource(
                        id = if (isEmptyQuery) R.string.empty_search_query else R.string.empty_search_results
                    )
                )
            }
        }
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(Dimens.big),
            horizontalAlignment = Alignment.CenterHorizontally,
            contentPadding = PaddingValues(Dimens.big),
            modifier = Modifier.fillMaxSize(),
        ) {
            items(movies, key = { it.id }) {
                MovieSearchItem(movie = it, onMovieClick = {})
            }
        }
    }
}

@Preview
@Composable
private fun SearchContentPreview() {
    MoviesTheme {
        SearchContent(
            isLoading = false,
            errorMessage = null,
            isEmptyQuery = false,
            movies = listOf(
                MoviesFakes.avatar,
                MoviesFakes.narnia,
                MoviesFakes.blackAdam
            )
        )
    }
}