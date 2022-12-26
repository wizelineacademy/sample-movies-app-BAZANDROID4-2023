package com.wizeline.coroutinesexercises.domain.entities

data class GenreWithMovies(
    val genre: Genre,
    val movies: List<Movie>
)