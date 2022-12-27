package com.wizeline.coroutinesexercises.domain.usecases

import com.wizeline.coroutinesexercises.domain.entities.GenreWithMovies
import com.wizeline.coroutinesexercises.domain.repositories.MoviesRepository
import com.wizeline.coroutinesexercises.utils.logExecutionTimeAndReturnResult
import javax.inject.Inject

class GetGenresWithMoviesUseCase @Inject constructor(
    private val moviesRepository: MoviesRepository
) {
    suspend operator fun invoke(): Result<List<GenreWithMovies>> = runCatching {
        logExecutionTimeAndReturnResult("getDataSequential") {
            getDataSequential()
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
        //  TODO:   Create an implementation to run coroutines in parallel and compare execution times
        return getDataSequential()
    }
}