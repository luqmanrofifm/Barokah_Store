package com.barokahstore.app.core.domain.usecase.remote

import com.barokahstore.app.core.data.remote.ResultApi
import com.barokahstore.app.core.data.remote.response.ErrorResponse
import com.barokahstore.app.core.data.remote.response.ResponseModel
import com.barokahstore.app.core.domain.repository.RemoteDataRepository
import javax.inject.Inject

class AddPriceListRemoteUseCase @Inject constructor(
    private val  remoteDataRepository: RemoteDataRepository
) {
    suspend fun invoke(nama: String, harga: Int, satuan: String, keterangan: String): ResultApi<ResponseModel.Result, ErrorResponse>{
        return  remoteDataRepository.addPriceList(nama, harga, satuan, keterangan)
    }
}