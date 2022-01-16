package com.application.sunstonekotlinassignment.repositories

import com.application.sunstonekotlinassignment.data.Resource
import com.application.sunstonekotlinassignment.data.ResponseHandler
import com.application.sunstonekotlinassignment.local.interfacemo.ApiClient
import com.application.sunstonekotlinassignment.local.reponse.ResponseModel
import javax.inject.Inject

class PhotoRepo @Inject constructor(val apiClient: ApiClient) {

    var responseHandler = ResponseHandler()

    suspend fun getData(query: String): Resource<ResponseModel> {
        val reposne = apiClient.getResponseFromAPI(query)
        return try {
            responseHandler.handleSuccess(reposne)
        } catch (e: Exception) {
            responseHandler.handleException(e)
        }
    }
}