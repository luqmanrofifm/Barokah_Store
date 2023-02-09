package com.example.barokahstore.core.data.remote

import android.util.Log
import com.example.barokahstore.core.data.local.entity.PriceListEntity
import com.example.barokahstore.core.data.remote.response.ErrorResponse
import com.example.barokahstore.core.data.remote.response.ResponseModel
import com.example.barokahstore.core.domain.repository.RemoteDataRepository
import com.example.barokahstore.core.utils.safeApiCall
import com.google.gson.Gson
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RemoteDataSourceImpl @Inject constructor(
    private  val apiService: ApiService
): RemoteDataRepository {
    override suspend fun getAllPriceList(): ResultApi<ResponseModel.Result, ErrorResponse> {
        return safeApiCall {
            val data = apiService.getData()
            if (data.isSuccessful){
                val bodyResponse = data.body()
                val result = Gson().fromJson(bodyResponse, ResponseModel.Result::class.java)
                //result.data.map { PriceListEntity(it.id, it.nama, it.price, it.deskripsi) }
                ResultApi.Success(result)
            } else {
                try {
                    val response = Gson().fromJson(data.errorBody()!!.charStream(), ErrorResponse::class.java)
                    ResultApi.Failure(response)
                } catch (e: Exception){
                    ResultApi.Failure(ErrorResponse(
                        status = "Failed",
                        message = "Request gagal"
                    ))
                }
            }
        }
    }

    override suspend fun addPriceList(nama: String, harga: Int, satuan: String, keterangan: String): ResultApi<ResponseModel.Result, ErrorResponse> {
        return safeApiCall {
            val data = apiService.addData(PriceListRequest(
                nama = nama,
                price = harga,
                satuan = satuan,
                deskripsi = keterangan
            ))

            if (data.isSuccessful){
                val bodyResponse = data.body()
                val result = Gson().fromJson(bodyResponse, ResponseModel.Result::class.java)
                //result.data.map { PriceListEntity(it.id, it.nama, it.price, it.deskripsi) }
                ResultApi.Success(result)
            } else {
                try {
                    val response = Gson().fromJson(data.errorBody()!!.charStream(), ErrorResponse::class.java)
                    ResultApi.Failure(response)
                } catch (e: Exception){
                    ResultApi.Failure(ErrorResponse(
                        status = "Failed",
                        message = "Request gagal"
                    ))
                }
            }
        }
    }

    override suspend fun updatePriceList(nama: String, harga: Int, satuan: String, keterangan: String, id: Int): ResultApi<ResponseModel.Result, ErrorResponse> {
        return safeApiCall {
            val data = apiService.updateData(PriceListRequest(
                nama = nama,
                price = harga,
                satuan = satuan,
                deskripsi = keterangan
            ), id)

            if (data.isSuccessful){
                val bodyResponse = data.body()
                val result = Gson().fromJson(bodyResponse, ResponseModel.Result::class.java)
                //result.data.map { PriceListEntity(it.id, it.nama, it.price, it.deskripsi) }
                ResultApi.Success(result)
            } else {
                try {
                    val response = Gson().fromJson(data.errorBody()!!.charStream(), ErrorResponse::class.java)
                    ResultApi.Failure(response)
                } catch (e: Exception){
                    ResultApi.Failure(ErrorResponse(
                        status = "Failed",
                        message = "Request gagal"
                    ))
                }
            }
        }
    }

    override suspend fun deletePriceList(id: Int): ResultApi<ResponseModel.Result, ErrorResponse> {
        return safeApiCall {
            val data = apiService.deleteData(id)
            if (data.isSuccessful){
                val bodyResponse = data.body()
                val result = Gson().fromJson(bodyResponse, ResponseModel.Result::class.java)
                ResultApi.Success(result)
            } else {
                try {
                    val response = Gson().fromJson(data.errorBody()!!.charStream(), ErrorResponse::class.java)
                    ResultApi.Failure(response)
                } catch (e: Exception){
                    ResultApi.Failure(ErrorResponse(
                        status = "Failed",
                        message = "Request gagal"
                    ))
                }
            }
        }
    }

}