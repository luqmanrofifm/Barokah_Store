package com.example.barokahstore.core.domain.usecase.remote

import com.example.barokahstore.core.data.remote.ResultApi
import com.example.barokahstore.core.data.remote.response.ErrorResponse
import com.example.barokahstore.core.data.remote.response.ResponseModel
import com.example.barokahstore.core.domain.repository.RemoteDataRepository
import javax.inject.Inject

class DeletePriceListRemoteUseCase @Inject constructor(
    private val  remoteDataRepository: RemoteDataRepository
) {
    suspend fun invoke(id: Int): ResultApi<ResponseModel.Result, ErrorResponse> {
        return  remoteDataRepository.deletePriceList(id)
    }
}