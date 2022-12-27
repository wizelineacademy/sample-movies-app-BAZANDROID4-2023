package com.wizeline.coroutinesexercises.ui.search

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ContentAlpha
import androidx.compose.material.LocalContentAlpha
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.wizeline.coroutinesexercises.domain.entities.Movie
import com.wizeline.coroutinesexercises.ui.components.AppImage
import com.wizeline.coroutinesexercises.ui.components.SimpleRatingBar
import com.wizeline.coroutinesexercises.ui.theme.Dimens
import com.wizeline.coroutinesexercises.ui.theme.MoviesTheme
import com.wizeline.coroutinesexercises.utils.fakes.MoviesFakes

@Composable
fun MovieSearchItem(
    movie: Movie,
    onMovieClick: (String) -> Unit
) {
    Surface(
        modifier = Modifier
            .clip(RoundedCornerShape(8.dp))
            .fillMaxWidth()
            .clickable { onMovieClick(movie.id) },
    ) {
        Row(
            modifier = Modifier.fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            AppImage(
                imageUrl = movie.posterUrl,
                modifier = Modifier.fillMaxWidth(0.2f),
                contentScale = ContentScale.FillWidth
            )
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(Dimens.standard)
            ) {
                Text(
                    text = movie.title,
                    style = MaterialTheme.typography.subtitle1
                )
                movie.year?.let {
                    Spacer(modifier = Modifier.height(Dimens.small))
                    CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
                        Text(
                            text = "($it)",
                            style = MaterialTheme.typography.body2
                        )
                    }
                }
                movie.rating?.let {
                    Spacer(modifier = Modifier.height(Dimens.small))
                    SimpleRatingBar(
                        ratingPercent = it,
                        width = 70.dp
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun MovieSearchItemPreview() {
    MoviesTheme {
        MovieSearchItem(
            movie = MoviesFakes.avatar,
            onMovieClick = {}
        )
    }
}