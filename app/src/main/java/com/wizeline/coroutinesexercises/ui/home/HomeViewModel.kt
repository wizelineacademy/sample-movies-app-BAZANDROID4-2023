package com.wizeline.coroutinesexercises.ui.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.concurrent.thread

private const val TAG = "HomeViewModel"

@HiltViewModel
class HomeViewModel @Inject constructor() : ViewModel() {

    init {
        launchCoroutines()
        //launchThreads()
    }

    private fun launchCoroutines() = viewModelScope.launch {
        repeat(100_000) {
            launch {
                delay(5000L)
                print(".")
            }
        }
    }

    private fun launchThreads() {
        repeat(100_000) {
            thread {
                Thread.sleep(5000L)
                print(".")
            }
        }
    }

}