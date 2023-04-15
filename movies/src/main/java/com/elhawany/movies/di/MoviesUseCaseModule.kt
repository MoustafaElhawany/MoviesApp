package com.elhawany.movies.di

import com.elhawany.movies.domain.repository.MoviesRepository
import com.elhawany.movies.domain.usecase.GetMovieDetails
import com.elhawany.movies.domain.usecase.GetMovies
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object MoviesUseCaseModule {

    @Provides
    fun provideGetMoviesUseCase(repository: MoviesRepository): GetMovies =
        GetMovies(repository)

    @Provides
    fun provideGetMovieDetailsUseCase(repository: MoviesRepository): GetMovieDetails =
        GetMovieDetails(repository)
}
