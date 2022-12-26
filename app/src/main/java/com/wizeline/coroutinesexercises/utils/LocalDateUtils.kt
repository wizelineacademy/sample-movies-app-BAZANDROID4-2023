package com.wizeline.coroutinesexercises.utils

import timber.log.Timber
import java.time.LocalDate

fun parseLocalDateOrNull(date: String?, movieId: String? = null): LocalDate? {
    return if (date.isNullOrEmpty()) {
        null
    } else {
        try {
            LocalDate.parse(date)
        } catch (e: Exception) {
            Timber.e("Couldn't parse date: $date from movieId = $movieId")
            null
        }
    }
}