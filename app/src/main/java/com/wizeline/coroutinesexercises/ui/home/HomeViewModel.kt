package com.wizeline.coroutinesexercises.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wizeline.coroutinesexercises.domain.usecases.GetGenresWithMoviesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getGenresWithMoviesUseCase: GetGenresWithMoviesUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState = _uiState.asStateFlow()

    private val exceptionHandler = CoroutineExceptionHandler { _, e ->
        _uiState.update {
            it.copy(isLoading = false, errorMessage = "Couldn't load data: ${e.message}")
        }
    }

    init {
        refreshMovies()
    }

    private fun refreshMovies() = viewModelScope.launch(exceptionHandler) {
        _uiState.update { it.copy(isLoading = true) }
        val moviesResult = getGenresWithMoviesUseCase()
        _uiState.update {
            it.copy(isLoading = false, genreSections = moviesResult)
        }
    }

}