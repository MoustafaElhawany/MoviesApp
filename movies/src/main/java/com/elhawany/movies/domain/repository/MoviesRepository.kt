package com.elhawany.movies.domain.repository

import com.elhawany.core.utils.ResultWrapper
import com.elhawany.movies.data.remote.dto.MovieDetailsResponse
import com.elhawany.movies.domain.model.Movie

interface MoviesRepository {
    suspend fun getMoviesList(apiKey: String): ResultWrapper<List<Movie>>

    suspend fun getMovieDetailsById(movieId: Int, apiKey: String): ResultWrapper<MovieDetailsResponse>
}