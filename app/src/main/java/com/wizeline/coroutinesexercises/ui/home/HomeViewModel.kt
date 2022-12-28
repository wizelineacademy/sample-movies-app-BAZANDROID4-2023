package com.wizeline.coroutinesexercises.ui.home

import androidx.lifecycle.ViewModel
import com.wizeline.coroutinesexercises.di.MainScheduler
import com.wizeline.coroutinesexercises.domain.usecases.GetGenresWithMoviesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.Scheduler
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getGenresWithMoviesUseCase: GetGenresWithMoviesUseCase,
    @MainScheduler private val mainScheduler: Scheduler
) : ViewModel() {

    val uiState: Flowable<HomeUiState> = getGenresWithMoviesUseCase()
        .toFlowable()
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
}