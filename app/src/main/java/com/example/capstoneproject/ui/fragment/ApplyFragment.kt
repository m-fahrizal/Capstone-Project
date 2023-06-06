package com.example.capstoneproject.ui.fragment

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.content.Intent.ACTION_GET_CONTENT
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import com.example.capstoneproject.data.create.ApplyPresenter
import com.example.capstoneproject.data.utils.uriToFile
import com.example.capstoneproject.databinding.FragmentApplyBinding
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import java.io.File

class ApplyFragment : Fragment() {
    private lateinit var binding: FragmentApplyBinding
    private var applyPresenter : ApplyPresenter? = null
    private var getFile: File? = null

    private val launcherIntentGallery = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == RESULT_OK) {
            val selectedImg = result.data?.data as Uri
            selectedImg.let { uri ->
                val myFile = uriToFile(uri, requireContext())
                getFile = myFile
                binding.previewImageView.setImageURI(uri)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

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
        binding.galleryButton.setOnClickListener { startGallery() }

        btnApply.setOnClickListener {
//            it.findNavController().navigate(R.id.UploadFragment)
            if (getFile != null) {
                val nik = "${binding.edNIK.text}".toRequestBody("text/plain".toMediaType())
                val nisn = "${binding.edNISN.text}".toRequestBody("text/plain".toMediaType())
                val npsn = "${binding.edNPSN.text}".toRequestBody("text/plain".toMediaType())
                val name = "${binding.edfullname.text}".toRequestBody("text/plain".toMediaType())
                val gender = "${binding.edGender.text}".toRequestBody("text/plain".toMediaType())
                val tempatLahir =
                    "${binding.edTempatLahir.text}".toRequestBody("text/plain".toMediaType())
                val tglLahir =
                    "${binding.edTglLahir.text}".toRequestBody("text/plain".toMediaType())
                val mom = "${binding.edMom.text}".toRequestBody("text/plain".toMediaType())
                val address = "${binding.edAddress.text}".toRequestBody("text/plain".toMediaType())
                val posCode = "${binding.edPosCode.text}".toRequestBody("text/plain".toMediaType())
                val religion =
                    "${binding.edReligion.text}".toRequestBody("text/plain".toMediaType())
                val salary = "${binding.edSalary.text}".toRequestBody("text/plain".toMediaType())
                val school = "${binding.edSchool.text}".toRequestBody("text/plain".toMediaType())
                val schoolStatus =
                    "${binding.edSchoolStatus.text}".toRequestBody("text/plain".toMediaType())
                val prestasi =
                    "${binding.rgPrestasi.checkedRadioButtonId}".toRequestBody("text/plain".toMediaType())
                val nilai = "${binding.edNilai.text}".toRequestBody("text/plain".toMediaType())
                val statusKip =
                    "${binding.rgStatusKip.checkedRadioButtonId}".toRequestBody("text/plain".toMediaType())
                val statusRumah =
                    "${binding.edStatusRumah.text}".toRequestBody("text/plain".toMediaType())
                val file = getFile as File
                val fotoRumah = file.asRequestBody("image/jpeg".toMediaTypeOrNull())
                val imgmultipart: MultipartBody.Part = MultipartBody.Part.createFormData(
                    "photo",
                    file.name,
                    fotoRumah
                )

                applyPresenter?.postData(
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
                    imgmultipart
                )
            }
        }

        applyPresenter = ApplyPresenter(this)
        applyPresenter?.getData()
    }

    private fun startGallery() {
        val intent = Intent()
        intent.action = ACTION_GET_CONTENT
        intent.type = "image/*"
        val chooser = Intent.createChooser(intent, "Pilih Gambar")
        launcherIntentGallery.launch(chooser)
    }

    fun showMessage(msg: String) {
        Toast.makeText(requireContext(), msg, Toast.LENGTH_SHORT).show()
    }

    fun showError(msg: String) {
        Toast.makeText(requireContext(), msg, Toast.LENGTH_SHORT).show()
    }

    private fun onAttachView() {
        applyPresenter?.onAttach(this)
    }

    private fun onDetachView() {
        applyPresenter?.onDetach()
    }

    override fun onStart() {
        super.onStart()
        onAttachView()
    }

    override fun onDestroy() {
        super.onDestroy()
        onDetachView()
    }
}