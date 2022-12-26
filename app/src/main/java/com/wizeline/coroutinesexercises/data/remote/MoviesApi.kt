package com.wizeline.coroutinesexercises.data.remote

import com.wizeline.coroutinesexercises.data.remote.dto.GenresResponse
import com.wizeline.coroutinesexercises.data.remote.dto.MoviesListResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface MoviesApi {

    @GET("genre/movie/list")
    suspend fun getGenres(): GenresResponse

    @GET("discover/movie")
    suspend fun getMoviesByGenre(@Query("with_genres") genreId: String): MoviesListResponse

    companion object {
        //  TODO:   Get your own API key at https://www.themoviedb.org/
        const val TMDB_KEY = "028b36691e206a986b4d8fc3e77651a1"
        const val BASE_URL = "https://api.themoviedb.org/3/"
        const val IMAGE_URL_PREFIX = "https://image.tmdb.org/t/p/original"
    }
}