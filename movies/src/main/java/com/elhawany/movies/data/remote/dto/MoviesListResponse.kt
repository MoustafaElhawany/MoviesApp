package com.elhawany.movies.data.remote.dto

data class MoviesListResponse(
    val page: Int,
    val results: List<MovieDto>,
    val total_pages: Int,
    val total_results: Int
)