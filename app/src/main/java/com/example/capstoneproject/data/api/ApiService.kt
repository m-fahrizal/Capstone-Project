package com.example.capstoneproject.data.api

import com.example.capstoneproject.data.model.KIPResponse
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.*

interface ApiService {
    @GET("getdata")
    fun getData() : Call<KIPResponse>

    @Multipart
    @POST("postdata")
    fun postData(
//        @Part ("nik") nik: String,
//        @Part ("nisn") nisn: String,
//        @Part ("npsn") npsn: String,
//        @Part ("nama_lengkap") namaLengkap: String,
//        @Part ("jenis_kelamin") jenisKelamin: String,
//        @Part ("tempat_lahir") tempatLahir: String,
//        @Part ("tanggal_lahir") tanggalLahir: String,
//        @Part ("nama_ibu") namaIbu: String,
//        @Part ("alamat") alamat: String,
//        @Part ("kode_pos") kodePos: String,
//        @Part ("agama") agama: String,
//        @Part ("gaji") gaji: String,
//        @Part ("asal_sekolah") asalSekolah: String,
//        @Part ("status_sekolah") statusSekolah: String,
//        @Part ("prestasi") prestasi: String,
//        @Part ("nilai_ujian") nilaiUjian: String,
//        @Part ("status_kip") statusKip: String,
//        @Part ("status_rumah") statusRumah: String,
//        @Part fotoRumah: MultipartBody.Part
        @Part ("nik") nik: RequestBody,
        @Part ("nisn") nisn: RequestBody,
        @Part ("npsn") npsn: RequestBody,
        @Part ("nama_lengkap") namaLengkap: RequestBody,
        @Part ("jenis_kelamin") jenisKelamin: RequestBody,
        @Part ("tempat_lahir") tempatLahir: RequestBody,
        @Part ("tanggal_lahir") tanggalLahir: RequestBody,
        @Part ("nama_ibu") namaIbu: RequestBody,
        @Part ("alamat") alamat: RequestBody,
        @Part ("kode_pos") kodePos: RequestBody,
        @Part ("agama") agama: RequestBody,
        @Part ("gaji") gaji: RequestBody,
        @Part ("asal_sekolah") asalSekolah: RequestBody,
        @Part ("status_sekolah") statusSekolah: RequestBody,
        @Part ("prestasi") prestasi: RequestBody,
        @Part ("nilai_ujian") nilaiUjian: RequestBody,
        @Part ("status_kip") statusKip: RequestBody,
        @Part ("status_rumah") statusRumah: RequestBody,
        @Part fotoRumah: MultipartBody.Part
    ) : Call<KIPResponse>

    @FormUrlEncoded
    @POST("uploadImage")
    fun postImage(

    ) : Call<KIPResponse>
}