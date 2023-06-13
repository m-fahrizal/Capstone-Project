package com.example.capstoneproject.data.ml

import com.example.capstoneproject.data.model.MLRequest
import com.example.capstoneproject.data.model.MLResponse
import retrofit2.Call
import retrofit2.http.*

interface MLService {
    @Headers("Content-Type: application/json")
    @POST("api_ml")
    fun postML(
        @Body data: MLRequest
    ): Call<MLResponse>
}