package com.barokahstore.app.core.domain.usecase.remote

import com.barokahstore.app.core.data.remote.ResultApi
import com.barokahstore.app.core.data.remote.response.ErrorResponse
import com.barokahstore.app.core.data.remote.response.ResponseModel
import com.barokahstore.app.core.domain.repository.RemoteDataRepository
import javax.inject.Inject

class DeletePriceListRemoteUseCase @Inject constructor(
    private val  remoteDataRepository: RemoteDataRepository
) {
    suspend fun invoke(id: Int): ResultApi<ResponseModel.Result, ErrorResponse> {
        return  remoteDataRepository.deletePriceList(id)
    }
}