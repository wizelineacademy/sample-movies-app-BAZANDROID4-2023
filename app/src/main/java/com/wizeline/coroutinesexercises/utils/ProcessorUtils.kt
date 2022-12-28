package com.wizeline.coroutinesexercises.utils

import io.reactivex.rxjava3.processors.BehaviorProcessor

inline fun <T : Any> BehaviorProcessor<T>.update(function: (T?) -> T?): Boolean {
    val prevValue = value
    val nextValue = function(prevValue)
    return if (nextValue != null) offer(nextValue) else false
}