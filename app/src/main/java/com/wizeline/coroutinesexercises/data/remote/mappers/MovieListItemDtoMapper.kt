package com.wizeline.coroutinesexercises.data.remote.mappers

import com.wizeline.coroutinesexercises.data.remote.MoviesApi
import com.wizeline.coroutinesexercises.data.remote.dto.MovieListItemDto
import com.wizeline.coroutinesexercises.domain.entities.Movie

fun MovieListItemDto.toMovie() = Movie(
    id = id,
    title = title ?: "",
    posterUrl = getImageUrlOrNull(posterPath) ?: ""
)

private fun getImageUrlOrNull(relativePath: String?) =
    relativePath?.let { MoviesApi.IMAGE_URL_PREFIX + it }