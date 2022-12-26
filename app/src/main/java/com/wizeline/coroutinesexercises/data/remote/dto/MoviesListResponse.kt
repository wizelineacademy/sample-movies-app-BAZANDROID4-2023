package com.wizeline.coroutinesexercises.data.remote.dto

import com.google.gson.annotations.SerializedName

data class MoviesListResponse(
    val page: Int?,
    val results: List<MovieListItemDto>?,
    @SerializedName("total_results") val totalResults: Int?,
    @SerializedName("total_pages") val totalPages: Int?
)