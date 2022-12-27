package com.wizeline.coroutinesexercises.domain.usecases

import com.wizeline.coroutinesexercises.domain.entities.GenreWithMovies
import com.wizeline.coroutinesexercises.domain.repositories.MoviesRepository
import com.wizeline.coroutinesexercises.utils.customRunCatching
import javax.inject.Inject

class GetGenresWithMoviesUseCase @Inject constructor(
    private val moviesRepository: MoviesRepository
) {
    suspend operator fun invoke(): Result<List<GenreWithMovies>> = customRunCatching {
        val genres = moviesRepository.getGenres()
        genres.map {
            val movies = moviesRepository.getMoviesByGenre(it.id)
            GenreWithMovies(it, movies)
        }
    }
}