package com.sudarshan.sunstoneassignment.local.interfaces

import com.sudarshan.sunstoneassignment.extras.Extras.API_KEY
import com.sudarshan.sunstoneassignment.local.responses.ResponseModel
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface APIClient {


    @Headers("Authorization: $API_KEY")
    @GET("search")
    suspend fun getResponseFromAPI(
        @Query("query") query: String,
    ): ResponseModel


}