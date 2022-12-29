package com.wizeline.coroutinesexercises.ui.home

import androidx.lifecycle.ViewModel
import com.wizeline.coroutinesexercises.di.MainScheduler
import com.wizeline.coroutinesexercises.domain.usecases.GetGenresWithMoviesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.processors.BehaviorProcessor
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getGenresWithMoviesUseCase: GetGenresWithMoviesUseCase,
    @MainScheduler private val mainScheduler: Scheduler
) : ViewModel() {

    private val _uiState = BehaviorProcessor.createDefault(HomeUiState())
    val uiState: Flowable<HomeUiState> = _uiState
    private val compositeDisposable = CompositeDisposable()

    init {
        refreshMovies()
    }

    private fun refreshMovies() {
        val moviesResult = getGenresWithMoviesUseCase()
        moviesResult
            .observeOn(mainScheduler)
            .doOnSubscribe {
                _uiState.offer(_uiState.valueOrDefault.copy(isLoading = true))
            }
            .subscribe(
                { data ->
                    _uiState.offer(
                        _uiState.valueOrDefault.copy(isLoading = false, genreSections = data)
                    )
                }, { e ->
                    _uiState.offer(
                        _uiState.valueOrDefault.copy(
                            isLoading = false,
                            errorMessage = "Couldn't load data: ${e.message}"
                        )
                    )
                }
            ).let { compositeDisposable.add(it) }
    }

    override fun onCleared() {
        compositeDisposable.clear()
        _uiState.onComplete()
        super.onCleared()
    }
}

val BehaviorProcessor<HomeUiState>.valueOrDefault get() = this.value ?: HomeUiState()