package com.barokahstore.app.core.domain.usecase.remote

import com.barokahstore.app.core.data.remote.ResultApi
import com.barokahstore.app.core.data.remote.response.ErrorResponse
import com.barokahstore.app.core.data.remote.response.ResponseModel
import com.barokahstore.app.core.domain.repository.RemoteDataRepository
import javax.inject.Inject

class UpdatePriceListRemoteUseCase @Inject constructor(
    private val  remoteDataRepository: RemoteDataRepository
) {
    suspend fun invoke(nama: String, harga: Int, satuan: String, keterangan: String, id: Int): ResultApi<ResponseModel.Result, ErrorResponse> {
        return  remoteDataRepository.updatePriceList(nama, harga, satuan, keterangan, id)
    }
}