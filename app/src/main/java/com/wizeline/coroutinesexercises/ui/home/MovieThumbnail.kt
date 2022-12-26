package com.wizeline.coroutinesexercises.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.wizeline.coroutinesexercises.domain.entities.Movie
import androidx.compose.material3.Surface
import com.wizeline.coroutinesexercises.ui.theme.MoviesTheme
import com.wizeline.coroutinesexercises.utils.MoviesFakes

@Composable
fun MovieThumbnail(
    modifier: Modifier = Modifier,
    movie: Movie,
    height: Dp = 150.dp,
    width: Dp = height * 2 / 3,
    onMovieClick: (movieId: String) -> Unit
) {
    Surface(
        modifier = modifier
            .width(width)
            .height(height)
            .clip(RoundedCornerShape(5.dp))
            .clickable { onMovieClick(movie.id) }
    ) {
        Image(
            modifier = Modifier.fillMaxSize(),
            imageUrl = movie.posterUrl
        )
    }
}

@Preview
@Composable
private fun MovieThumbnailPreview() {
    MoviesTheme {
        MovieThumbnail(
            movie = MoviesFakes.avatar,
            onMovieClick = {}
        )
    }
}