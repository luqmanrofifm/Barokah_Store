package com.example.barokahstore.core.data.remote

import com.google.gson.JsonObject
import retrofit2.Response
import retrofit2.http.*

interface ApiService {

    @GET("/")
    suspend fun getData(): Response<JsonObject>

    @Headers("Content-Type: application/json")
    @POST("api/item/")
    suspend fun addData(
        @Body payload: PriceListRequest
    ): Response<JsonObject>

    @PUT("/")
    suspend fun updateData(
        @Body payload: PriceListRequest
    ): Response<JsonObject>

    @DELETE("/{id}")
    suspend fun deleteData(
        @Path("id") id: Int
    ): Response<JsonObject>
}