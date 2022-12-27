package com.wizeline.coroutinesexercises.utils.fakes

import com.wizeline.coroutinesexercises.domain.entities.Genre
import com.wizeline.coroutinesexercises.domain.entities.Movie
import com.wizeline.coroutinesexercises.domain.repositories.MoviesRepository
import kotlinx.coroutines.delay
import javax.inject.Inject

class FakeMoviesRepository @Inject constructor(): MoviesRepository {
    override suspend fun getGenres(): List<Genre> {
        delay(2000)
        with(GenresFakes) {
            return listOf(genre1, genre2, genre3)
        }
    }

    override suspend fun getMoviesByGenre(genreId: String): List<Movie> {
        delay(2000)
        with(MoviesFakes) {
            return listOf(avatar, blackAdam, troll, narnia, pinocchio)
        }
    }

    override suspend fun searchMovies(query: String): List<Movie> {
        delay(2000)
        with(MoviesFakes) {
            return listOf(avatar, blackAdam, troll, narnia, pinocchio)
        }
    }
}