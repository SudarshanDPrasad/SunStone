package com.application.sunstonekotlinassignment.di

import com.application.sunstonekotlinassignment.local.interfacemo.ApiClient
import com.application.sunstonekotlinassignment.extras.Extras.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object MovieModule {

    @Singleton
    @Provides
    fun ProvidesApi(): ApiClient {
        val builder = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        return builder.create(ApiClient::class.java)
    }
}