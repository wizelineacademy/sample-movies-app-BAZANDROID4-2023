package com.wizeline.coroutinesexercises.ui.search

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.wizeline.coroutinesexercises.di.MainScheduler
import com.wizeline.coroutinesexercises.domain.usecases.SearchMoviesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.processors.BehaviorProcessor
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import java.util.concurrent.TimeUnit
import javax.inject.Inject

@OptIn(FlowPreview::class)
@HiltViewModel
class SearchViewModel @Inject constructor(
    private val searchMoviesUseCase: SearchMoviesUseCase,
    @MainScheduler private val mainScheduler: Scheduler,
    savedStateHandle: SavedStateHandle,
) : ViewModel() {

    private val _uiState = MutableStateFlow(SearchUiState())
    val uiState = _uiState.asStateFlow()
    private val compositeDisposable = CompositeDisposable()
    private val query = BehaviorProcessor.create<String>()

    fun setQuery(query: String) {
        this.query.onNext(query)
    }

    fun clearQuery() {
        this.query.onNext("")
    }

    init {
        val initialQuery = savedStateHandle[QUERY_SAVED_STATE_KEY] ?: DEFAULT_QUERY
        query.onNext(initialQuery)

        query
            .doOnEach { query ->
                _uiState.update { it.copy(query = query.value ?: DEFAULT_QUERY, isLoading = true) }
            }
            .debounce(300, TimeUnit.MILLISECONDS)
            .flatMap { query ->
                searchMoviesUseCase(query).toFlowable()
            }
            .observeOn(mainScheduler)
            .subscribe({ data ->
                _uiState.update { it.copy(isLoading = false, movies = data) }
            }, { e ->
                _uiState.update {
                    it.copy(
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