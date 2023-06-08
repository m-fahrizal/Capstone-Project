package com.example.capstoneproject.data.api

import androidx.viewbinding.BuildConfig
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import java.util.concurrent.TimeUnit


class ApiConfig {
    fun getApiService(): ApiService {
        val loggingInterceptor =
            HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
        val client = OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()
        val retrofit = Retrofit.Builder()
            .baseUrl("https://backend-dot-mykip-387204.et.r.appspot.com")
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
        return retrofit.create(ApiService::class.java)
    }
}

//object ApiConfig {
//    private fun getInterceptor(): OkHttpClient {
//        val logging = HttpLoggingInterceptor()
//        logging.apply {
//            logging.level = HttpLoggingInterceptor.Level.BODY
//        }
//        return OkHttpClient.Builder()
//            .retryOnConnectionFailure(true)
//            .readTimeout(10, TimeUnit.SECONDS)
//            .writeTimeout(10, TimeUnit.SECONDS)
//            .connectTimeout(10, TimeUnit.SECONDS)
//            .addInterceptor(logging)
//            .build()
//    }
//
//    private fun initGson(): Gson {
//        return GsonBuilder().setLenient().create()
//    }
//
//    const val baseUrl = "https://backend-dot-mykip-387204.et.r.appspot.com"
//
//    fun getRetrofit(): Retrofit {
//        return Retrofit.Builder()
//            .baseUrl(baseUrl)
//            .addConverterFactory(GsonConverterFactory.create(initGson()))
//            .client(getInterceptor())
//            .build()
//    }
//
//    fun getService(): ApiService {
//        return getRetrofit().create(ApiService::class.java)
//    }
//}