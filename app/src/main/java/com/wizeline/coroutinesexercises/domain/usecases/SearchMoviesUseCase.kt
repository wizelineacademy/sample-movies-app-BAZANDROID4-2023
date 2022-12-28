package com.wizeline.coroutinesexercises.domain.usecases

import com.wizeline.coroutinesexercises.domain.entities.Movie
import com.wizeline.coroutinesexercises.domain.repositories.MoviesRepository
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class SearchMoviesUseCase @Inject constructor(
    private val moviesRepository: MoviesRepository
) {
    operator fun invoke(query: String): Single<List<Movie>> {
        return if (query.isBlank()) {
            Single.just(emptyList())
        } else {
            moviesRepository.searchMovies(query)
        }
    }
}