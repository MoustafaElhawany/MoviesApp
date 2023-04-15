package com.elhawany.movies.di

import com.elhawany.movies.data.remote.MoviesApiService
import com.elhawany.movies.data.repository.MoviesRepositoryImpl
import com.elhawany.movies.domain.repository.MoviesRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object MoviesRepositoryModule {

    @Provides
    fun provideMoviesRepository(apiService: MoviesApiService): MoviesRepository =
        MoviesRepositoryImpl(apiService)
}