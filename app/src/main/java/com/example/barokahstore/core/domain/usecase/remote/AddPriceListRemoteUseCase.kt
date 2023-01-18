package com.example.barokahstore.core.domain.usecase.remote

import com.example.barokahstore.core.data.local.entity.PriceListEntity
import com.example.barokahstore.core.data.remote.ResultApi
import com.example.barokahstore.core.data.remote.response.ErrorResponse
import com.example.barokahstore.core.data.remote.response.ResponseModel
import com.example.barokahstore.core.domain.repository.RemoteDataRepository
import javax.inject.Inject

class AddPriceListRemoteUseCase @Inject constructor(
    private val  remoteDataRepository: RemoteDataRepository
) {
    suspend fun invoke(nama: String, harga: Int, keterangan: String): ResultApi<ResponseModel.Result, ErrorResponse>{
        return  remoteDataRepository.addPriceList(nama, harga, keterangan)
    }
}