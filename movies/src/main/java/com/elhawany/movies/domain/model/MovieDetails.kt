package com.elhawany.movies.domain.model

data class MovieDetails(
    val id: Int,
    val adult: Boolean,
    val genres: List<Genre>,
    val overview: String,
    val posterUrl: String,
    val releaseYear:String,
    val title: String,
    val rating: Double,
    val backdropUrl: String?,
)