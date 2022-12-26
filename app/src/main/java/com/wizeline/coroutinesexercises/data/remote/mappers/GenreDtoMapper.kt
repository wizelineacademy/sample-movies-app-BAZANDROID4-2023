package com.wizeline.coroutinesexercises.data.remote.mappers

import com.wizeline.coroutinesexercises.data.remote.dto.GenreDto
import com.wizeline.coroutinesexercises.domain.entities.Genre

fun GenreDto.toGenre(): Genre = Genre(
    id = id,
    name = name ?: "",
)