package com.wizeline.coroutinesexercises.data.remote.mappers

import com.wizeline.coroutinesexercises.data.remote.dto.GenresResponse
import com.wizeline.coroutinesexercises.domain.entities.Genre

fun GenresResponse.toGenreList(): List<Genre> =
    genres?.map { it.toGenre() } ?: emptyList()