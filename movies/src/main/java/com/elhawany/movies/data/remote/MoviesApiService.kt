package com.elhawany.movies.data.remote

import com.elhawany.core.BuildConfig
import com.elhawany.movies.data.remote.dto.MovieDetailsResponse
import com.elhawany.movies.data.remote.dto.MoviesListResponse
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MoviesApiService {

    @GET("discover/movie")
    fun getMovies(
        @Query("api_key") apiKey: String = BuildConfig.ApiKey
    ): Deferred<MoviesListResponse>

    @GET("discover/movie")
    fun getMovies(
        @Query("api_key") apiKey: String = BuildConfig.ApiKey,
        @Query("page") page: Int
    ): Deferred<MoviesListResponse>

    @GET("movie/{movie_id}")
    fun getMovieDetailsById(
        @Path("movie_id") movieId: Int,
        @Query("api_key") apiKey: String = BuildConfig.ApiKey
    ): Deferred<MovieDetailsResponse>
}
