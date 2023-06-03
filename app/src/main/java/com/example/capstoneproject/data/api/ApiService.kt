package com.example.capstoneproject.data.api

import com.example.capstoneproject.data.model.KIPResponse
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.*
import java.io.File

interface ApiService {
    @GET("getdata")
    fun getData() : Call<KIPResponse>

    @Multipart
    @POST("postdata")
    fun postData(
        @Part ("nik") nik: Int,
        @Part ("nisn") nisn: Int,
        @Part ("npsn") npsn: Int,
        @Part ("nama_lengkap") namaLengkap: String,
        @Part ("jenis_kelamin") jenisKelamin: String,
        @Part ("tempat_lahir") tempatLahir: String,
        @Part ("tanggal_lahir") tanggalLahir: String,
        @Part ("nama_ibu") namaIbu: String,
        @Part ("alamat") alamat: String,
        @Part ("kode_pos") kodePos: Int,
        @Part ("agama") agama: String,
        @Part ("perguruan_tinggi") perguruanTinggi: String,
        @Part ("prestasi") prestasi: Boolean,
        @Part ("nama_univ") namaUniv: String,
        @Part ("jurusan") jurusan: String,
        @Part ("jenjang") jenjang: String,
        @Part ("semester") semester: Int,
        @Part ("ukt") ukt: Int,
        @Part ("ipk") ipk: Float,
        @Part ("foto_rumah") fotoRumah: File
    ) : Call<KIPResponse>

    @FormUrlEncoded
    @POST("uploadImage")
    fun postImage(

    ) : Call<KIPResponse>
}