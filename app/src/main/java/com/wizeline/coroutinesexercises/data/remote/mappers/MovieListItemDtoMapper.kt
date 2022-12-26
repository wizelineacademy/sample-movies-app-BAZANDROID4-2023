package com.wizeline.coroutinesexercises.data.remote.mappers

import com.wizeline.coroutinesexercises.data.remote.MoviesApi
import com.wizeline.coroutinesexercises.data.remote.dto.MovieListItemDto
import com.wizeline.coroutinesexercises.domain.entities.Movie
import com.wizeline.coroutinesexercises.utils.parseLocalDateOrNull

private const val NO_INFO_AVAILABLE = "No information available"

fun MovieListItemDto.toMovie() = Movie(
    id = id,
    title = if (!title.isNullOrBlank()) title else originalTitle ?: NO_INFO_AVAILABLE,
    posterUrl = getImageUrlOrNull(posterPath) ?: "",
    backdropUrl = getImageUrlOrNull(backdropPath) ?: "",
    overview = overview ?: NO_INFO_AVAILABLE,
    genreIds = genreIds ?: emptyList(),
    rating = voteAverage?.let { (it * 10).toInt() },
    releaseDate = parseLocalDateOrNull(releaseDate, movieId = id),
    runtimeMinutes = null
)

private fun getImageUrlOrNull(relativePath: String?) =
    relativePath?.let { MoviesApi.IMAGE_URL_PREFIX + it }