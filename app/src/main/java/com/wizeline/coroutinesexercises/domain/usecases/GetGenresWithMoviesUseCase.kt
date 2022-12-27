package com.wizeline.coroutinesexercises.domain.usecases

import com.wizeline.coroutinesexercises.domain.entities.GenreWithMovies
import com.wizeline.coroutinesexercises.domain.repositories.MoviesRepository
import com.wizeline.coroutinesexercises.utils.logExecutionTimeAndReturnResult
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import javax.inject.Inject

class GetGenresWithMoviesUseCase @Inject constructor(
    private val moviesRepository: MoviesRepository
) {
    suspend operator fun invoke(): Result<List<GenreWithMovies>> = runCatching {
        logExecutionTimeAndReturnResult {
            getDataAsync()
        }
    }

    private suspend fun getDataSequential(): List<GenreWithMovies> {
        val genres = moviesRepository.getGenres()
        return genres.map {
            val movies = moviesRepository.getMoviesByGenre(it.id)
            GenreWithMovies(it, movies)
        }
    }

    private suspend fun getDataAsync(): List<GenreWithMovies> {
        val genres = moviesRepository.getGenres()
        val data = coroutineScope {
            genres.map { genre ->
                async {
                    val movies = moviesRepository.getMoviesByGenre(genre.id)
                    GenreWithMovies(genre, movies)
                }
            }
        }
        return data.awaitAll()
    }
}

/*
Execution times:
    MoviesRepositoryImpl:
        getDataSequential:  3823 ms
        getDataASync:       2527 ms
    FakeMoviesRepository (consistent times as there is no cache):
        getDataSequential:  8137 ms
        getDataAsync:       4070 ms
 */