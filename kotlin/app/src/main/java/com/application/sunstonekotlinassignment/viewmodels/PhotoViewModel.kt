package com.application.sunstonekotlinassignment.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.application.sunstonekotlinassignment.data.Resource
import com.application.sunstonekotlinassignment.local.reponse.ResponseModel
import com.application.sunstonekotlinassignment.repositories.PhotoRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject


@HiltViewModel
class PhotoViewModel @Inject constructor(private val repo: PhotoRepo) : ViewModel() {

    fun getResponseFromApi(query: String): LiveData<Resource<ResponseModel>> {
        return liveData(Dispatchers.IO) {
            val reponse = repo.getData(query)
            emit(reponse)
        }
    }

}