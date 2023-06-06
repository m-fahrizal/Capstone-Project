package com.example.capstoneproject.data.model

import com.google.gson.annotations.SerializedName
import okhttp3.MultipartBody
import java.io.File
import java.util.*

data class KIPResponse(

	@field:SerializedName("KIPResponse")
	val kipResponse: List<KIPResponseItem?>? = null,

	@field:SerializedName("message")
	val message: String? = null,

	@field:SerializedName("status")
	val status: Int? = null
)

data class KIPResponseItem(

	@field:SerializedName("nik")
	val nik: Int? = null,

	@field:SerializedName("nisn")
	val nisn: Int? = null,

	@field:SerializedName("npsn")
	val npsn: Int? = null,

	@field:SerializedName("nama_lengkap")
	val namaLengkap: String? = null,

	@field:SerializedName("jenis_kelamin")
	val jenisKelamin: String? = null,

	@field:SerializedName("tempat_lahir")
	val tempatLahir: String? = null,

	@field:SerializedName("tanggal_lahir")
	val tanggalLahir: String? = null,

	@field:SerializedName("nama_ibu")
	val namaIbu: String? = null,

	@field:SerializedName("alamat")
	val alamat: String? = null,

	@field:SerializedName("kode_pos")
	val kodePos: Int? = null,

	@field:SerializedName("agama")
	val agama: String? = null,

	@field:SerializedName("gaji")
	val gaji: Int? = null,

	@field:SerializedName("asal_sekolah")
	val asalSekolah: String? = null,

	@field:SerializedName("status_sekolah")
	val statusSekolah: String? = null,

	@field:SerializedName("prestasi")
	val prestasi: Boolean? = null,

	@field:SerializedName("nilai_ujian")
	val nilaiUjian: Float? = null,

	@field:SerializedName("status_kip")
	val statusKip: Boolean? = null,

	@field:SerializedName("status_rumah")
	val statusRumah: String? = null,

	@field:SerializedName("foto_rumah")
	val fotoRumah: MultipartBody? = null,
)
