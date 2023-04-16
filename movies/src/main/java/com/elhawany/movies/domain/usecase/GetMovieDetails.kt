package com.elhawany.movies.domain.usecase

import com.elhawany.core.utils.ResultWrapper
import com.elhawany.movies.domain.model.MovieDetails
import com.elhawany.movies.domain.repository.MoviesRepository

class GetMovieDetails(
    private val repository: MoviesRepository
) {
    suspend operator fun invoke(movieId: Int): ResultWrapper<MovieDetails> {
        return repository.getMovieDetailsById(movieId)
    }
}
