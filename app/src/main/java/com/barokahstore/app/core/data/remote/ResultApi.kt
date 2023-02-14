package com.barokahstore.app.core.data.remote

sealed class ResultApi<out Success, out Failure> {
    data class Success<out Success>(val value: Success) : ResultApi<Success, Nothing>()
    data class Failure<out Failure>(val reason: Failure) : ResultApi<Nothing, Failure>()
}

internal fun <T> ResultApi.Success<T>.toSuccess(): ResultApi.Success<T> {
    return ResultApi.Success(this.value)
}