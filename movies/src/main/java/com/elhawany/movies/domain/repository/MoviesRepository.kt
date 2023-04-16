package com.elhawany.movies.domain.repository

import com.elhawany.core.utils.ResultWrapper
import com.elhawany.movies.domain.model.Movie
import com.elhawany.movies.domain.model.MovieDetails

interface MoviesRepository {
    suspend fun getMoviesList(): ResultWrapper<List<Movie>>

    suspend fun getMovieDetailsById(movieId: Int): ResultWrapper<MovieDetails>
}