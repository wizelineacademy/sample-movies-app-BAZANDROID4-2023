package com.wizeline.coroutinesexercises.ui.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.wizeline.coroutinesexercises.domain.entities.Genre
import com.wizeline.coroutinesexercises.domain.entities.Movie
import com.wizeline.coroutinesexercises.ui.components.MovieThumbnail
import com.wizeline.coroutinesexercises.ui.theme.Dimens
import com.wizeline.coroutinesexercises.ui.theme.MoviesTheme
import com.wizeline.coroutinesexercises.utils.GenresFakes
import com.wizeline.coroutinesexercises.utils.MoviesFakes

@Composable
fun GenreSection(
    genre: Genre,
    movies: List<Movie>,
    onMovieClick: (movieId: String) -> Unit
) {
    Column {
        Text(text = genre.name, style = MaterialTheme.typography.headlineSmall)
        Spacer(modifier = Modifier.height(Dimens.standard))
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(Dimens.standard)
        ) {
            items(movies, key = { it.id }) { movie ->
                MovieThumbnail(movie = movie, onMovieClick = onMovieClick)
            }
        }
    }
}

@Preview
@Composable
private fun GenreSectionPreview() {
    MoviesTheme {
        GenreSection(
            genre = GenresFakes.genre1,
            movies = listOf(
                MoviesFakes.avatar,
                MoviesFakes.troll,
                MoviesFakes.narnia
            ),
            onMovieClick = {}
        )
    }
}