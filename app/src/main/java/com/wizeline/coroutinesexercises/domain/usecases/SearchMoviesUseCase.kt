package com.wizeline.coroutinesexercises.domain.usecases

import com.wizeline.coroutinesexercises.domain.entities.Movie
import com.wizeline.coroutinesexercises.domain.repositories.MoviesRepository
import com.wizeline.coroutinesexercises.utils.customRunCatching
import timber.log.Timber
import javax.inject.Inject

class SearchMoviesUseCase @Inject constructor(
    private val moviesRepository: MoviesRepository
) {
    init {
        Timber.d("Creating SearchMoviesUseCase...")
    }

    suspend operator fun invoke(query: String): Result<List<Movie>> = customRunCatching {
        if (query.isBlank()) {
            emptyList()
        } else {
            moviesRepository.searchMovies(query)
        }
    }
}