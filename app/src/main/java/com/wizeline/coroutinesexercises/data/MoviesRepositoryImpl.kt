package com.wizeline.coroutinesexercises.data

import com.wizeline.coroutinesexercises.data.remote.MoviesApi
import com.wizeline.coroutinesexercises.data.remote.mappers.toGenreList
import com.wizeline.coroutinesexercises.data.remote.mappers.toMovieList
import com.wizeline.coroutinesexercises.di.IoScheduler
import com.wizeline.coroutinesexercises.domain.entities.Genre
import com.wizeline.coroutinesexercises.domain.entities.Movie
import com.wizeline.coroutinesexercises.domain.repositories.MoviesRepository
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class MoviesRepositoryImpl @Inject constructor(
    private val moviesApi: MoviesApi,
    @IoScheduler private val ioScheduler: Scheduler
) : MoviesRepository {

    override fun getGenres(): Single<List<Genre>> =
        moviesApi.getGenres()
            .map { it.toGenreList() }
            .subscribeOn(ioScheduler)

    override fun getMoviesByGenre(genreId: String): Single<List<Movie>> =
        moviesApi.getMoviesByGenre(genreId)
            .map { it.toMovieList() }
            .subscribeOn(ioScheduler)

    override fun searchMovies(query: String): Single<List<Movie>> =
        moviesApi.searchMoviesByName(query)
            .map { it.toMovieList() }
            .subscribeOn(ioScheduler)

}