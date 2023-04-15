package com.elhawany.movies.domain.usecase

import com.elhawany.core.utils.ResultWrapper
import com.elhawany.movies.data.remote.dto.MovieDetailsResponse
import com.elhawany.movies.domain.repository.MoviesRepository

class GetMovieDetails(
    private val repository: MoviesRepository
) {
    suspend operator fun invoke(movieId: Int, apiKey: String): ResultWrapper<MovieDetailsResponse> {
        return repository.getMovieDetailsById(movieId, apiKey)
    }
}
