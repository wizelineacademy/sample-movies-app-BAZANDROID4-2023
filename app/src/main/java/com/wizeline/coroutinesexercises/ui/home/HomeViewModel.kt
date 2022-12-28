package com.wizeline.coroutinesexercises.ui.home

import androidx.lifecycle.ViewModel
import com.wizeline.coroutinesexercises.di.MainScheduler
import com.wizeline.coroutinesexercises.domain.usecases.GetGenresWithMoviesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.disposables.CompositeDisposable
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getGenresWithMoviesUseCase: GetGenresWithMoviesUseCase,
    @MainScheduler private val mainScheduler: Scheduler
) : ViewModel() {

    private val compositeDisposable = CompositeDisposable()

    val uiState: Flowable<HomeUiState> = getGenresWithMoviesUseCase()
        .toFlowable()
        .doOnEach { Timber.d("doOnEach before replay!, state = ${it.value}") }
        .map {
            HomeUiState(
                isLoading = false,
                genreSections = it
            )
        }
        .onErrorReturn {
            HomeUiState(
                isLoading = false,
                errorMessage = it.message
            )
        }
        .startWithItem(HomeUiState(isLoading = true))
        .observeOn(mainScheduler)
        .replay(1)
        .autoConnect(1) { compositeDisposable.add(it) }
        .doOnEach { Timber.d("doOnEach after replay!, state = ${it.value}") }

    override fun onCleared() {
        compositeDisposable.clear()
        super.onCleared()
    }
}