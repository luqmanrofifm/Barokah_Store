package com.barokahstore.app.core.data.remote

import com.google.gson.JsonObject
import retrofit2.Response
import retrofit2.http.*

interface ApiService {

    @Headers("Content-Type: application/json")
    @GET("api/item/")
    suspend fun getData(): Response<JsonObject>

    @Headers("Content-Type: application/json")
    @POST("api/item")
    suspend fun addData(
        @Body payload: PriceListRequest
    ): Response<JsonObject>

    @Headers("Content-Type: application/json")
    @PUT("api/item/{id}")
    suspend fun updateData(
        @Body payload: PriceListRequest,
        @Path("id") id: Int
    ): Response<JsonObject>

    @Headers("Content-Type: application/json")
    @DELETE("api/item/{id}")
    suspend fun deleteData(
        @Path("id") id: Int
    ): Response<JsonObject>
}