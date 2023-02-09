package com.example.barokahstore.core.domain.repository

import com.example.barokahstore.core.data.local.entity.PriceListEntity
import com.example.barokahstore.core.data.remote.ResultApi
import com.example.barokahstore.core.data.remote.response.ErrorResponse
import com.example.barokahstore.core.data.remote.response.ResponseModel

interface RemoteDataRepository {
    suspend fun getAllPriceList(): ResultApi<ResponseModel.Result,ErrorResponse>
    suspend fun addPriceList(nama: String, harga: Int, satuan: String, keterangan: String): ResultApi<ResponseModel.Result,ErrorResponse>
    suspend fun updatePriceList(nama: String, harga: Int, satuan: String, keterangan: String, id: Int): ResultApi<ResponseModel.Result,ErrorResponse>
    suspend fun deletePriceList(id: Int): ResultApi<ResponseModel.Result,ErrorResponse>
}