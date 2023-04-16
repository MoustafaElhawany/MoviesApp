package com.elhawany.moviesapp.feature_movies.moviesList

import androidx.lifecycle.*
import com.elhawany.core.utils.ResultWrapper
import com.elhawany.core.utils.BaseViewModel
import com.elhawany.movies.domain.model.Movie
import com.elhawany.movies.domain.usecase.GetMovies
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MoviesViewModel @Inject constructor(
    private val getMoviesUseCase: GetMovies
) : BaseViewModel() {

    private val _moviesList = MutableLiveData<List<Movie>>()
    val moviesList: LiveData<List<Movie>> = _moviesList

    fun getMoviesList() {
        _isLoading.value = true
        viewModelScope.launch {
            when (val response: ResultWrapper<List<Movie>> = getMoviesUseCase()) {
                is ResultWrapper.Success -> response.value?.let { _moviesList.value = it }
                else -> onResponseError(response)
            }

            _isLoading.value = false
        }
    }
}