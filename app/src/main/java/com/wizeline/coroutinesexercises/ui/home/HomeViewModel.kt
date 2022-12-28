package com.wizeline.coroutinesexercises.ui.home

import androidx.lifecycle.ViewModel
import com.wizeline.coroutinesexercises.di.MainScheduler
import com.wizeline.coroutinesexercises.domain.usecases.GetGenresWithMoviesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.disposables.CompositeDisposable
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getGenresWithMoviesUseCase: GetGenresWithMoviesUseCase,
    @MainScheduler private val mainScheduler: Scheduler
) : ViewModel() {

    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState = _uiState.asStateFlow()
    private val compositeDisposable = CompositeDisposable()

    init {
        refreshMovies()
    }

    private fun refreshMovies() {
        val moviesResult = getGenresWithMoviesUseCase()
        moviesResult
            .observeOn(mainScheduler)
            .doOnSubscribe { _uiState.update { it.copy(isLoading = true) } }
            .subscribe(
                { data ->
                    _uiState.update {
                        it.copy(isLoading = false, genreSections = data)
                    }
                }, { e ->
                    _uiState.update {
                        it.copy(isLoading = false, errorMessage = "Couldn't load data: ${e.message}")
                    }
                }
            ).let { compositeDisposable.add(it) }
    }

    override fun onCleared() {
        compositeDisposable.clear()
        super.onCleared()
    }

}