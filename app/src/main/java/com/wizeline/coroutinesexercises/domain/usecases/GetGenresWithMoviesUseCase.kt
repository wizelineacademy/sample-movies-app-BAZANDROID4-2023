package com.wizeline.coroutinesexercises.domain.usecases

import com.wizeline.coroutinesexercises.di.IoScheduler
import com.wizeline.coroutinesexercises.domain.entities.GenreWithMovies
import com.wizeline.coroutinesexercises.domain.repositories.MoviesRepository
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class GetGenresWithMoviesUseCase @Inject constructor(
    private val moviesRepository: MoviesRepository,
    @IoScheduler private val ioScheduler: Scheduler
) {
    operator fun invoke(): Single<List<GenreWithMovies>> =
        getFromFlatMap()

    private fun getFromFlatMap() =
        moviesRepository
            .getGenres()
            .toFlowable()
            .flatMap { Flowable.fromIterable(it) }
            .flatMap { genre ->
                moviesRepository.getMoviesByGenre(genre.id)
                    .toFlowable()
                    .map { movies -> GenreWithMovies(genre, movies) }
            }
            .toList()

    private fun getFromParallel() =
        moviesRepository
            .getGenres()
            .toFlowable()
            .flatMap { Flowable.fromIterable(it) }
            .parallel()
            .runOn(ioScheduler)
            .map { genre ->
                val movies = moviesRepository.getMoviesByGenre(genre.id).blockingGet()
                GenreWithMovies(genre, movies)
            }
            .sequential()
            .toList()
}