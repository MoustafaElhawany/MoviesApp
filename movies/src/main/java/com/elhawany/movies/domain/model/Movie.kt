package com.elhawany.movies.domain.model

data class Movie(
    val id: Int,
    val title: String,
    val overview: String,
    val posterUrl: String,
    val releaseYear:String,
    val rating: Double,
)