package com.example.capstoneproject.data.model

import com.google.gson.annotations.SerializedName

data class MLRequest(
    @field:SerializedName("prestasi")
    val prestasi: Int? = null,

    @field:SerializedName("nilai_ujian")
    val nilaiUjian: Float? = null,

    @field:SerializedName("gaji_ortu")
    val gaji_ortu: Float? = null,

    @field:SerializedName("status_kip")
    val statusKip: Int? = null,

    @field:SerializedName("status_rumah")
    val statusRumah: Int? = null,

    @field:SerializedName("foto_rumah")
    val fotoRumah: String? = null
)
