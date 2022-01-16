package com.application.sunstonekotlinassignment.local.interfacemo

import com.application.sunstonekotlinassignment.extras.Extras.API_KEY
import com.application.sunstonekotlinassignment.local.reponse.ResponseModel
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface ApiClient {


    @Headers("Authorization: $API_KEY")
    @GET("search")
    suspend fun getResponseFromAPI(
        @Query("query") query: String
    ): ResponseModel

}