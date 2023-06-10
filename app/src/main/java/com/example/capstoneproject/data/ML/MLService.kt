package com.example.capstoneproject.data.ML

import com.example.capstoneproject.data.model.KIPResponse
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

//interface MLService {
//    @Multipart
//    @POST
//    fun postML(
//        @Part("prestasi") prestasi: RequestBody,
//        @Part("nilai_ujian") nilaiUjian: RequestBody,
//        @Part("gaji") gaji: RequestBody,
//        @Part("status_kip") statusKip: RequestBody,
//        @Part("status_rumah") statusRumah: RequestBody,
//        @Part fotoRumah: MultipartBody.Part
//    ) : Call<MLResponse>
//}