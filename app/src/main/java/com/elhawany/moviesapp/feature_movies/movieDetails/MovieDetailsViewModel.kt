package com.elhawany.moviesapp.feature_movies.movieDetails

import androidx.lifecycle.*
import com.elhawany.core.utils.ResultWrapper
import com.elhawany.movies.domain.usecase.GetMovieDetails
import com.elhawany.core.utils.BaseViewModel
import com.elhawany.movies.domain.model.MovieDetails
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieDetailsViewModel @Inject constructor(
    private val getMovieDetailsUseCase: GetMovieDetails
) : BaseViewModel() {

    private val _movieDetails = MutableLiveData<MovieDetails>()
    val movieDetails: LiveData<MovieDetails> = _movieDetails

    fun getMovieDetailsById(movieId: Int) {
        _isLoading.value = true
        viewModelScope.launch {
            when (val response: ResultWrapper<MovieDetails> = getMovieDetailsUseCase(movieId)) {
                is ResultWrapper.Success -> response.value?.let { _movieDetails.value = it }
                else -> onResponseError(response)
            }

            _isLoading.value = false
        }
    }
}