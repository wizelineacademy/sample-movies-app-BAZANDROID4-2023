package com.wizeline.coroutinesexercises.domain.usecases

import com.wizeline.coroutinesexercises.domain.entities.GenreWithMovies
import com.wizeline.coroutinesexercises.domain.repositories.MoviesRepository
import javax.inject.Inject

class GetGenresWithMoviesUseCase @Inject constructor(
    private val moviesRepository: MoviesRepository
) {
    suspend operator fun invoke(): Result<List<GenreWithMovies>> = runCatching {
        val genres = moviesRepository.getGenres()
        genres.map {
            val movies = moviesRepository.getMoviesByGenre(it.id)
            GenreWithMovies(it, movies)
        }
    }
}