package com.wizeline.coroutinesexercises.ui.search

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wizeline.coroutinesexercises.domain.usecases.SearchMoviesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@OptIn(FlowPreview::class)
@HiltViewModel
class SearchViewModel @Inject constructor(
    private val searchMoviesUseCase: SearchMoviesUseCase,
    private val savedStateHandle: SavedStateHandle,
) : ViewModel() {

    private val _uiState = MutableStateFlow(SearchUiState())
    val uiState = _uiState.asStateFlow()

    private val query = savedStateHandle.getStateFlow(QUERY_SAVED_STATE_KEY, "")

    fun setQuery(query: String) {
        savedStateHandle[QUERY_SAVED_STATE_KEY] = query
    }

    fun clearQuery() {
        savedStateHandle[QUERY_SAVED_STATE_KEY] = ""
    }

    init {
        viewModelScope.launch {
            query
                .onEach { query ->
                    _uiState.update { it.copy(query = query, isLoading = true) }
                }
                .debounce(300)
                .map { query -> searchMoviesUseCase(query) }
                .collect { movies ->
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
    }

    companion object {
        const val QUERY_SAVED_STATE_KEY = "query"
        const val DEFAULT_QUERY = ""
    }
}