package com.example.capstoneproject.ui.fragment

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import com.example.capstoneproject.data.api.ApiConfig
import com.example.capstoneproject.data.model.KIPResponse
import com.example.capstoneproject.data.utils.uriToFile
import com.example.capstoneproject.databinding.FragmentApplyBinding
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.File

class ApplyFragment : Fragment() {
    private lateinit var binding: FragmentApplyBinding
    private var getFile: File? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentApplyBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val btnApply = binding.btnApply
        binding.selectImage.setOnClickListener { startGallery() }


        btnApply.setOnClickListener {
            postData()
//            it.findNavController().navigate(R.id.UploadFragment)
        }
    }

    private fun startGallery() {
        val intent = Intent()
        intent.action = Intent.ACTION_GET_CONTENT
        intent.type = "image/*"
        val chooser = Intent.createChooser(intent, "Pilih Gambar")
        launcherIntentGallery.launch(chooser)
    }

    private fun postData() {
        if (getFile != null) {
            val file = reduceFileImage(getFile as File)

            val nik = "${binding.edNIK.text}".toRequestBody("text/plain".toMediaType())
            val nisn = "${binding.edNISN.text}".toRequestBody("text/plain".toMediaType())
            val npsn = "${binding.edNPSN.text}".toRequestBody("text/plain".toMediaType())
            val name = "${binding.edfullname.text}".toRequestBody("text/plain".toMediaType())
            val gender = "${binding.edGender.text}".toRequestBody("text/plain".toMediaType())
            val tempatLahir = "${binding.edTempatLahir.text}".toRequestBody("text/plain".toMediaType())
            val tglLahir = "${binding.edTglLahir.text}".toRequestBody("text/plain".toMediaType())
            val mom = "${binding.edMom.text}".toRequestBody("text/plain".toMediaType())
            val address = "${binding.edAddress.text}".toRequestBody("text/plain".toMediaType())
            val posCode = "${binding.edPosCode.text}".toRequestBody("text/plain".toMediaType())
            val religion = "${binding.edReligion.text}".toRequestBody("text/plain".toMediaType())
            val salary = "${binding.edSalary.text}".toRequestBody("text/plain".toMediaType())
            val school = "${binding.edSchool.text}".toRequestBody("text/plain".toMediaType())
            val schoolStatus = "${binding.edSchoolStatus.text}".toRequestBody("text/plain".toMediaType())
            val prestasi = isPunyaPrestasi(binding).toRequestBody("text/plain".toMediaType())
            val nilai = "${binding.edNilai.text}".toRequestBody("text/plain".toMediaType())
            val statusKip = isPunyaKIP(binding).toRequestBody("text/plain".toMediaType())
            val statusRumah = checkStatusRumah(binding).toRequestBody("text/plain".toMediaType())
            val requestImageFile = file.asRequestBody("image/jpeg".toMediaType())
            val fotoRumah: MultipartBody.Part = MultipartBody.Part.createFormData(
                "foto_rumah",
                file.name,
                requestImageFile
            )
            val apiService = ApiConfig().getApiService()
            val uploadDataRequest = apiService.postData(
                nik,
                nisn,
                npsn,
                name,
                gender,
                tempatLahir,
                tglLahir,
                mom,
                address,
                posCode,
                religion,
                salary,
                school,
                schoolStatus,
                prestasi,
                nilai,
                statusKip,
                statusRumah,
                fotoRumah
            )
            uploadDataRequest.enqueue(object : Callback<KIPResponse> {
                override fun onResponse(
                    call: Call<KIPResponse>,
                    response: Response<KIPResponse>
                ) {
                    if (response.isSuccessful) {
                        val responseBody = response.body()
                        if (responseBody != null && !responseBody.error) {
                            Toast.makeText(requireContext(), responseBody.message, Toast.LENGTH_SHORT).show()
                            Log.d("tes", uploadDataRequest.toString())
                        }
                    } else {
                        Toast.makeText(requireContext(), response.message(), Toast.LENGTH_SHORT).show()
                    }
                }
                override fun onFailure(call: Call<KIPResponse>, t: Throwable) {
                    Toast.makeText(requireContext(), t.message, Toast.LENGTH_SHORT).show()
                    Log.d("tes", uploadDataRequest.toString())
                    Log.d("msg", t.message.toString())
                }
            })
        } else {
            Toast.makeText(requireContext(), "Silakan masukkan berkas gambar terlebih dahulu.", Toast.LENGTH_SHORT).show()
        }
    }

    private fun reduceFileImage(file: File): File {
        return file
    }

    private val launcherIntentGallery = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            val selectedImg = result.data?.data as Uri
            selectedImg.let { uri ->
                val myFile = uriToFile(uri, requireContext())
                getFile = myFile
                binding.previewImageView.setImageURI(uri)
            }
        }
    }

    private fun isPunyaPrestasi(binding:FragmentApplyBinding): String {
        var value = ""
        if (binding.havePrestasi.isChecked){
            value = "1"
        }
        if(binding.noPrestasi.isChecked){
            value = "0"
        }
        return value
    }

    private fun isPunyaKIP(binding:FragmentApplyBinding): String {
        var value = ""
        if (binding.haveKip.isChecked){
            value = "1"
        }
        if(binding.noKip.isChecked){
            value = "0"
        }
        return value
    }

    private fun checkStatusRumah(binding:FragmentApplyBinding): String {
        var value = ""
        if (binding.sewa.isChecked){
            value = "1"
        }
        if(binding.sendiri.isChecked){
            value = "2"
        }
        if(binding.numpang.isChecked){
            value = "3"
        }
        return value
    }
}