package com.example.capstoneproject.data.ML

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class MLConfig {
    fun getMLService(): MLService {
        val loggingInterceptor =
            HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
        val client = OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .readTimeout(90, TimeUnit.SECONDS) // Timeout diatur dalam detik
            .build()
        val retrofit = Retrofit.Builder()
            .baseUrl("https://asia-southeast2-mykip-387204.cloudfunctions.net/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
        return retrofit.create(MLService::class.java)
    }
}
