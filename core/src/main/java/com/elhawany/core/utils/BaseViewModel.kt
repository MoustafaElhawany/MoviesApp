package com.elhawany.core.utils

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.elhawany.core.R
import com.elhawany.core.utils.ui.ErrorMsg
import com.elhawany.core.utils.ui.UiText

open class BaseViewModel : ViewModel() {

    protected val _errorMsg = MutableLiveData<ErrorMsg>()
    val errorMsg: LiveData<ErrorMsg> = _errorMsg

    protected val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    fun onResponseError(error: ResultWrapper<Any>) {
        when (error) {
            is ResultWrapper.GenericError -> {
                _errorMsg.value = ErrorMsg(
                    text = UiText.StringResource(R.string.something_went_wrong)
                )
            }

            is ResultWrapper.NetworkError -> {
                _errorMsg.value = ErrorMsg(
                    text = UiText.StringResource(R.string.no_internet)
                )
            }

            is ResultWrapper.Success -> {
                // Do Nothing
            }
        }
    }
}