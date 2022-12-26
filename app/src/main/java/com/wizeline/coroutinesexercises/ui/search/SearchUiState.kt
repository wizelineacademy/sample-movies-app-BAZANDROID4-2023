package com.wizeline.coroutinesexercises.ui.search

import com.wizeline.coroutinesexercises.domain.entities.Movie

data class SearchUiState(
    val isLoading: Boolean = false,
    val query: String = "",
    val movies: List<Movie> = emptyList(),
    val errorMessage: String? = null,
)