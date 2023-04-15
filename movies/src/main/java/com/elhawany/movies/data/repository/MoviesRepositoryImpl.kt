package com.elhawany.movies.data.repository

import com.elhawany.core.utils.ResultWrapper
import com.elhawany.core.utils.safeApiCall
import com.elhawany.movies.data.mapper.toMovies
import com.elhawany.movies.data.remote.MoviesApiService
import com.elhawany.movies.data.remote.dto.MovieDetailsResponse
import com.elhawany.movies.domain.model.Movie
import com.elhawany.movies.domain.repository.MoviesRepository
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

class MoviesRepositoryImpl @Inject constructor(
    private val apiService: MoviesApiService,
) : MoviesRepository {

    override suspend fun getMoviesList(apiKey: String): ResultWrapper<List<Movie>> {
        val response = safeApiCall(Dispatchers.IO) {
            apiService.getMovies(apiKey)
        }

        return when (response) {
            is ResultWrapper.Success -> {
                ResultWrapper.Success(response.value?.results?.toMovies())
            }
            is ResultWrapper.GenericError -> {
                ResultWrapper.GenericError(response.code, response.error)
            }
            is ResultWrapper.NetworkError -> ResultWrapper.NetworkError
        }
    }

    override suspend fun getMovieDetailsById(
        movieId: Int,
        apiKey: String
    ): ResultWrapper<MovieDetailsResponse> {
        return safeApiCall(Dispatchers.IO) {
            apiService.getMovieDetailsById(movieId, apiKey)
        }
    }

}