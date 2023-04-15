package com.elhawany.core.utils

import com.google.gson.Gson
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.io.IOException

suspend fun <T> safeApiCall(
    dispatcher: CoroutineDispatcher,
    apiCall: suspend () -> Deferred<T>
): ResultWrapper<T> {
    return withContext(dispatcher) {
        try {
            ResultWrapper.Success(apiCall.invoke().await())
        } catch (throwable: Throwable) {
            when (throwable) {
                is HttpException -> {
                    val code = throwable.code()
                    val errorResponse = convertErrorBody(throwable)
                    ResultWrapper.GenericError(code, errorResponse)
                }
                is IOException -> ResultWrapper.NetworkError
                else -> {
                    ResultWrapper.GenericError(null, null)
                }
            }
        }
    }
}

private fun convertErrorBody(throwable: HttpException): ErrorResponse? {
    return try {
        throwable.response()?.errorBody()?.let {
            val gson = Gson()
            val error: ErrorResponse = gson.fromJson(
                it.string(),
                ErrorResponse::class.java
            )
            error
        }
    } catch (exception: Exception) {
        exception.printStackTrace()
        null
    }
}