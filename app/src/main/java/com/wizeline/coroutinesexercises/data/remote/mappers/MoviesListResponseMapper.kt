package com.wizeline.coroutinesexercises.data.remote.mappers

import com.wizeline.coroutinesexercises.data.remote.dto.MoviesListResponse
import com.wizeline.coroutinesexercises.domain.entities.Movie

fun MoviesListResponse.toMovieList(): List<Movie> =
    results?.map { it.toMovie() } ?: emptyList()