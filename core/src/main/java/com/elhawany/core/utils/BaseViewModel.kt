package com.elhawany.core.utils

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.elhawany.core.R
import com.elhawany.core.utils.ui.UiError
import com.elhawany.core.utils.ui.UiText

open class BaseViewModel : ViewModel() {

    protected val _uiError = MutableLiveData<UiError>()
    val uiError: LiveData<UiError> = _uiError

    protected val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    fun onResponseError(error: ResultWrapper<Any>) {
        when (error) {
            is ResultWrapper.GenericError -> {
                _uiError.value = UiError(
                    text = UiText.StringResource(R.string.something_went_wrong)
                )
            }

            is ResultWrapper.NetworkError -> {
                _uiError.value = UiError(
                    text = UiText.StringResource(R.string.no_internet)
                )
            }

            is ResultWrapper.Success -> {
                // Do Nothing
            }
        }
    }
}