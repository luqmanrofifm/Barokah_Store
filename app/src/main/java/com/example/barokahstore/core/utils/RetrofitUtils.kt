package com.example.barokahstore.core.utils

import com.example.barokahstore.core.data.remote.ResultApi
import com.example.barokahstore.core.data.remote.response.ErrorResponse
import java.io.IOException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

suspend fun <T : Any> safeApiCall(requestFunc: suspend () -> ResultApi<T, ErrorResponse>): ResultApi<T, ErrorResponse> {

    return try {
        requestFunc.invoke()
    } catch (e: Exception) {

        e.printStackTrace()

        when (e) {

            is  SocketTimeoutException -> {
                ResultApi.Failure(ErrorResponse(
                    status = "Failed",
                    message = "Server Timeout, coba lagi nanti"
                ))
            }

            is UnknownHostException -> {
                ResultApi.Failure(ErrorResponse(
                    status = "Failed",
                    message = "Server error"
                ))
            }

            is IOException -> {
                ResultApi.Failure(ErrorResponse(
                    status = "Failed",
                    message = "Format tidak valid"
                ))
            }

            else -> {
                ResultApi.Failure(ErrorResponse(
                    status = "Failed",
                    message = "Request gagal"
                ))
            }
        }
    }
}