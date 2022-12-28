package com.wizeline.coroutinesexercises.domain.repositories

import com.wizeline.coroutinesexercises.domain.entities.Genre
import com.wizeline.coroutinesexercises.domain.entities.Movie
import io.reactivex.rxjava3.core.Single

interface MoviesRepository {
    fun getGenres(): Single<List<Genre>>
    fun getMoviesByGenre(genreId: String): Single<List<Movie>>
    fun searchMovies(query: String): Single<List<Movie>>
}