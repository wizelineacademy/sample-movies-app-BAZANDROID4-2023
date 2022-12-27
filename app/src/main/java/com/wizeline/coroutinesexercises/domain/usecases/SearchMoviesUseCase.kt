package com.wizeline.coroutinesexercises.domain.usecases

import com.wizeline.coroutinesexercises.domain.entities.Movie
import com.wizeline.coroutinesexercises.domain.repositories.MoviesRepository
import com.wizeline.coroutinesexercises.utils.customRunCatching
import javax.inject.Inject

class SearchMoviesUseCase @Inject constructor(
    private val moviesRepository: MoviesRepository
) {
    suspend operator fun invoke(query: String): Result<List<Movie>> = customRunCatching {
        if (query.isBlank()) {
            emptyList()
        } else {
            moviesRepository.searchMovies(query)
        }
    }
}