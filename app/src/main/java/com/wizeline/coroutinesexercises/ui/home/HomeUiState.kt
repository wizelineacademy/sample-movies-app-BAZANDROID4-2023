package com.wizeline.coroutinesexercises.ui.home

import com.wizeline.coroutinesexercises.domain.entities.GenreWithMovies

data class HomeUiState(
    val isLoading: Boolean = false,
    val genreSections: List<GenreWithMovies> = emptyList(),
    val errorMessage: String? = null,
)