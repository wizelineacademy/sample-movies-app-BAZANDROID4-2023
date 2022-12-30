package com.wizeline.coroutinesexercises.data

import com.wizeline.coroutinesexercises.data.remote.MoviesApi
import com.wizeline.coroutinesexercises.data.remote.mappers.toGenreList
import com.wizeline.coroutinesexercises.data.remote.mappers.toMovieList
import com.wizeline.coroutinesexercises.di.IoDispatcher
import com.wizeline.coroutinesexercises.domain.entities.Genre
import com.wizeline.coroutinesexercises.domain.entities.Movie
import com.wizeline.coroutinesexercises.domain.repositories.MoviesRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import timber.log.Timber
import javax.inject.Inject

class MoviesRepositoryImpl @Inject constructor(
    private val moviesApi: MoviesApi,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
) : MoviesRepository {

    init {
        Timber.d("MoviesApiInstance: ${this.moviesApi}")
    }

    override suspend fun getGenres(): List<Genre> = withContext(ioDispatcher) {
        moviesApi.getGenres().toGenreList()
    }

    override suspend fun getMoviesByGenre(genreId: String): List<Movie> =
        withContext(ioDispatcher) {
            moviesApi.getMoviesByGenre(genreId).toMovieList()
        }

    override suspend fun searchMovies(query: String): List<Movie> = withContext(ioDispatcher) {
        moviesApi.searchMoviesByName(query).toMovieList()
    }

}