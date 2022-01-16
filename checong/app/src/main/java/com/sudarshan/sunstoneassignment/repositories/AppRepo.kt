package com.sudarshan.sunstoneassignment.repositories

import com.sudarshan.sunstoneassignment.local.Resource
import com.sudarshan.sunstoneassignment.local.ResponseHandler
import com.sudarshan.sunstoneassignment.local.interfaces.APIClient
import com.sudarshan.sunstoneassignment.local.responses.ResponseModel
import javax.inject.Inject

class AppRepo @Inject constructor(val apiClient: APIClient) {

    private val responseHandler = ResponseHandler()

    suspend fun getResponseFromAPI(query: String): Resource<ResponseModel> {
        val response = apiClient.getResponseFromAPI(query)
        return try {
            responseHandler.handleSuccess(response)
        } catch (e: Exception) {
            responseHandler.handleException(e)
        }
    }
}