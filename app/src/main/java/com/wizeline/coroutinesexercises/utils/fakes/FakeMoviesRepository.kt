package com.wizeline.coroutinesexercises.utils.fakes

import com.wizeline.coroutinesexercises.domain.entities.Genre
import com.wizeline.coroutinesexercises.domain.entities.Movie
import com.wizeline.coroutinesexercises.domain.repositories.MoviesRepository
import io.reactivex.rxjava3.core.Single
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class FakeMoviesRepository @Inject constructor() : MoviesRepository {
    override fun getGenres(): Single<List<Genre>> = with(GenresFakes) {
        return Single.just(listOf(genre1, genre2, genre3))
            .delaySubscription(2000, TimeUnit.MILLISECONDS)
    }

    override fun getMoviesByGenre(genreId: String): Single<List<Movie>> = with(MoviesFakes) {
        Single.just(listOf(avatar, blackAdam, troll, narnia, pinocchio))
            .delaySubscription(2000, TimeUnit.MILLISECONDS)
    }

    override fun searchMovies(query: String): Single<List<Movie>> = with(MoviesFakes) {
        Single.just(listOf(avatar, blackAdam, troll, narnia, pinocchio))
            .delaySubscription(2000, TimeUnit.MILLISECONDS)
    }
}