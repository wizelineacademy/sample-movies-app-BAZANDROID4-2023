package com.wizeline.coroutinesexercises.utils

import io.reactivex.rxjava3.core.Single
import timber.log.Timber
import kotlin.time.ExperimentalTime
import kotlin.time.measureTimedValue

@OptIn(ExperimentalTime::class)
inline fun <T> logExecutionTimeAndReturnResult(name: String = "", block: () -> T): T {
    val (value, time) = measureTimedValue { block() }
    Timber.d("$name Execution time in millis ${time.inWholeMilliseconds}")
    return value
}

fun <T : Any> Single<T>.logExecutionTime(name: String = ""): Single<T> {
    var start = 0L
    return this
        .doOnSubscribe {
            start = System.currentTimeMillis()
        }.doOnTerminate {
            Timber.d("$name Execution time in millis ${System.currentTimeMillis() - start}")
        }
}