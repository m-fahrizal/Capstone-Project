package com.example.capstoneproject.data.create
//
//import com.example.capstoneproject.data.api.ApiConfig
//import com.example.capstoneproject.data.base.BasePresenter
//import com.example.capstoneproject.data.model.KIPResponse
//import com.example.capstoneproject.ui.fragment.ApplyFragment
//import okhttp3.MultipartBody
//import retrofit2.Call
//import retrofit2.Callback
//import retrofit2.Response
//import java.io.File
//
//class ApplyPresenter(private var applyView: ApplyFragment) : BasePresenter<ApplyConstruct.view>, ApplyConstruct.Presenter{
//
//    private var getFile: File? = null
//
//    override fun onAttach(view: ApplyFragment) {
//        applyView = view
//    }
//
//    override fun onDetach() {
//        applyView
//    }
//
//    override fun getData() {
////        ApiConfig.getService().getData().enqueue(object: Callback<KIPResponse> {
////            override fun onResponse(call: Call<KIPResponse>, response: Response<KIPResponse>) {
////                if (response.isSuccessful || response.code() == 200) {
////                    val msg = response.body()?.message
////                    val sukses = response.body()?.message
////
////                    if (sukses != null) {
////                        val dataKIP = response.body()?.kipResponse
////                        mainView.showDataKIP(dataKIP)
////                    } else {
////                        mainView.showM
////                    }
////                }
////            }
////
////            override fun onFailure(call: Call<KIPResponse>, t: Throwable) {
////
////            }
////        })
//    }
//
//    fun postData(
//        nik: Int,
//        nisn: Int,
//        npsn: Int,
//        namaLengkap: String,
//        jenisKelamin: String,
//        tempatLahir: String,
//        tanggalLahir: String,
//        namaIbu: String,
//        alamat: String,
//        kodePos: Int,
//        agama: String,
//        gaji: Int,
//        asalSekolah: String,
//        statusSekolah: String,
//        prestasi: Boolean,
//        nilaiUjian: Float,
//        statusKip: Boolean,
//        statusRumah: String,
//        fotoRumah: MultipartBody.Part
////        nik: Int,
////        nisn: Int,
////        npsn: Int,
////        namaLengkap: String,
////        jenisKelamin: String,
////        tempatLahir: String,
////        tanggalLahir: String,
////        namaIbu: String,
////        alamat: String,
////        kodePos: Int,
////        agama: String,
////        gaji: Int,
////        asalSekolah: String,
////        statusSekolah: String,
////        prestasi: Boolean,
////        nilaiUjian: Float,
////        statusKip: Boolean,
////        statusRumah: String,
////        fotoRumah: MultipartBody.Part
//    ) {
//
//        ApiConfig.getService().postData(
//            nik,
//            nisn,
//            npsn,
//            namaLengkap,
//            jenisKelamin,
//            tempatLahir,
//            tanggalLahir,
//            namaIbu,
//            alamat,
//            kodePos,
//            agama,
//            gaji,
//            asalSekolah,
//            statusSekolah,
//            prestasi,
//            nilaiUjian,
//            statusKip,
//            statusRumah,
//            fotoRumah,
//        ).enqueue(object : Callback<KIPResponse> {
//            override fun onResponse(call: Call<KIPResponse>, response: Response<KIPResponse>) {
//                if (response.isSuccessful || response.code() == 200) {
//                    val msg = response.body()?.message
//                    val sukses = response.body()?.status
//
//                    if (sukses != null) {
////                        mainView.onSuccessPost()
//                        applyView.showMessage(msg.toString())
//                        println(applyView.toString())
//                    } else {
//                        applyView.showMessage(msg.toString())
//                        println(applyView.toString())
//                    }
//                }
//            }
//
//            override fun onFailure(call: Call<KIPResponse>, t: Throwable) {
//                applyView.showError(t.message.toString())
//                println(applyView.toString())
//            }
//
//        })
//
//    }
//
//    private fun reduceFileImage(file: File): File {
//        return file
//    }
//
//}