package com.elhawany.movies.domain.usecase

import com.elhawany.core.utils.ResultWrapper
import com.elhawany.movies.domain.model.Movie
import com.elhawany.movies.domain.repository.MoviesRepository

class GetMovies(
    private val repository: MoviesRepository
) {
    suspend operator fun invoke(): ResultWrapper<List<Movie>> {
        return repository.getMoviesList()
    }
}
