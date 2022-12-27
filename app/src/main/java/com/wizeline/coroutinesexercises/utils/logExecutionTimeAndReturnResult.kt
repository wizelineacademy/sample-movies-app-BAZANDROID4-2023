package com.wizeline.coroutinesexercises.utils

import timber.log.Timber
import kotlin.time.ExperimentalTime
import kotlin.time.measureTimedValue

@OptIn(ExperimentalTime::class)
inline fun <T> logExecutionTimeAndReturnResult(name: String = "", block: () -> T): T {
    val (value, time) = measureTimedValue { block() }
    Timber.d("$name Execution time in millis ${time.inWholeMilliseconds}")
    return value
}