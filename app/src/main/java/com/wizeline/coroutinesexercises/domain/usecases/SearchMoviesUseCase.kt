package com.wizeline.coroutinesexercises.domain.usecases

import com.wizeline.coroutinesexercises.domain.entities.Movie
import com.wizeline.coroutinesexercises.domain.repositories.MoviesRepository
import javax.inject.Inject

class SearchMoviesUseCase @Inject constructor(
    private val moviesRepository: MoviesRepository
) {
    suspend operator fun invoke(query: String): Result<List<Movie>> = runCatching {
        if (query.isBlank()) {
            emptyList()
        } else {
            moviesRepository.searchMovies(query)
        }
    }
}