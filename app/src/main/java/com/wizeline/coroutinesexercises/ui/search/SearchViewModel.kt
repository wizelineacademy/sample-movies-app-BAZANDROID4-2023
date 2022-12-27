package com.wizeline.coroutinesexercises.ui.search

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wizeline.coroutinesexercises.domain.usecases.SearchMoviesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val searchMoviesUseCase: SearchMoviesUseCase,
    savedStateHandle: SavedStateHandle,
) : ViewModel() {

    private val _uiState = MutableStateFlow(SearchUiState())
    val uiState = _uiState.asStateFlow()
    private var searchJob: Job? = null
    private val initialQuery = savedStateHandle[QUERY_SAVED_STATE_KEY] ?: DEFAULT_QUERY

    init {
        setQuery(initialQuery)
    }

    fun setQuery(query: String) = search(query)
    fun clearQuery() = search("")

    fun search(query: String) {
        _uiState.update { it.copy(isLoading = true, query = query) }
        searchJob?.cancel()
        searchJob = viewModelScope.launch {
            val movies = searchMoviesUseCase(query)
            movies.fold(
                onSuccess = { data ->
                    _uiState.update { it.copy(isLoading = false, movies = data) }
                },
                onFailure = { e ->
                    _uiState.update {
                        it.copy(
                            isLoading = false,
                            errorMessage = e.message
                        )
                    }
                }
            )
        }
    }

    companion object {
        const val QUERY_SAVED_STATE_KEY = "query"
        const val DEFAULT_QUERY = ""
    }
}