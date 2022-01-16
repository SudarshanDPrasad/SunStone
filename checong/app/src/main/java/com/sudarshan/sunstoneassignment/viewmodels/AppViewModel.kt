package com.sudarshan.sunstoneassignment.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.sudarshan.sunstoneassignment.local.Resource
import com.sudarshan.sunstoneassignment.local.responses.ResponseModel
import com.sudarshan.sunstoneassignment.repositories.AppRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import retrofit2.http.Query
import javax.inject.Inject


@HiltViewModel
class AppViewModel @Inject constructor(private val appRepo: AppRepo) : ViewModel() {

    fun getResponseFromAPI(query: String): LiveData<Resource<ResponseModel>> {
        return liveData(Dispatchers.IO) {
            val response = appRepo.getResponseFromAPI(query)
            emit(response)
        }
    }
}