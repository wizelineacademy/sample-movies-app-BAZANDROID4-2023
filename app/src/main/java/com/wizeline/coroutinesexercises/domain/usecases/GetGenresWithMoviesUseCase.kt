package com.wizeline.coroutinesexercises.domain.usecases

import com.wizeline.coroutinesexercises.di.IoScheduler
import com.wizeline.coroutinesexercises.domain.entities.GenreWithMovies
import com.wizeline.coroutinesexercises.domain.repositories.MoviesRepository
import com.wizeline.coroutinesexercises.utils.logExecutionTime
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class GetGenresWithMoviesUseCase @Inject constructor(
    private val moviesRepository: MoviesRepository,
    @IoScheduler private val ioScheduler: Scheduler
) {
    operator fun invoke(): Single<List<GenreWithMovies>> =
        getFromFlatMap().logExecutionTime()

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

/*
Execution times:

Network Request:
    flatMap:    1552
    parallel:   1518

FakeMoviesRepository:
    flatMap:    4025
    parallel:   4048
 */