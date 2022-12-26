package com.wizeline.coroutinesexercises.domain.repositories

import com.wizeline.coroutinesexercises.domain.entities.Genre
import com.wizeline.coroutinesexercises.domain.entities.Movie

interface MoviesRepository {
    suspend fun getGenres(): List<Genre>
    suspend fun getMoviesByGenre(genreId: String): List<Movie>
}