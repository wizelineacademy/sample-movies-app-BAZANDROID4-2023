package com.wizeline.coroutinesexercises.ui.search

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.wizeline.coroutinesexercises.di.MainScheduler
import com.wizeline.coroutinesexercises.domain.usecases.SearchMoviesUseCase
import com.wizeline.coroutinesexercises.utils.update
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.processors.BehaviorProcessor
import java.util.concurrent.TimeUnit
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val searchMoviesUseCase: SearchMoviesUseCase,
    @MainScheduler private val mainScheduler: Scheduler,
    savedStateHandle: SavedStateHandle,
) : ViewModel() {

    private val _uiState = BehaviorProcessor.createDefault(SearchUiState())
    val uiState: Flowable<SearchUiState> = _uiState
    private val query = BehaviorProcessor.createDefault(
        savedStateHandle[QUERY_SAVED_STATE_KEY] ?: DEFAULT_QUERY
    )
    private val compositeDisposable = CompositeDisposable()

    fun setQuery(query: String) {
        this.query.update { query }
    }

    fun clearQuery() {
        this.query.update { "" }
    }

    init {
        query
            .doOnEach { query ->
                _uiState.update { it?.copy(query = query.value ?: DEFAULT_QUERY, isLoading = true) }
            }
            .debounce(300, TimeUnit.MILLISECONDS)
            .flatMap { query ->
                searchMoviesUseCase(query).toFlowable()
            }
            .observeOn(mainScheduler)
            .subscribe({ data ->
                _uiState.update { it?.copy(isLoading = false, movies = data) }
            }, { e ->
                _uiState.update {
                    it?.copy(
                        isLoading = false,
                        errorMessage = e.message
                    )
                }
            }).let { compositeDisposable.add(it) }
    }

    override fun onCleared() {
        compositeDisposable.clear()
        super.onCleared()
    }

    companion object {
        const val QUERY_SAVED_STATE_KEY = "query"
        const val DEFAULT_QUERY = ""
    }
}