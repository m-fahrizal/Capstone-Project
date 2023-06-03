package com.example.capstoneproject.ui.fragment

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.content.Intent.ACTION_GET_CONTENT
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import com.example.capstoneproject.R
import com.example.capstoneproject.data.create.ApplyPresenter
import com.example.capstoneproject.data.utils.uriToFile
import com.example.capstoneproject.databinding.FragmentApplyBinding
import com.example.capstoneproject.databinding.FragmentProfileBinding
import okhttp3.MediaType.Companion.toMediaType
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
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentApplyBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val btnApply = binding.btnApply
        binding.galleryButton.setOnClickListener { startGallery() }

        btnApply.setOnClickListener{
//            it.findNavController().navigate(R.id.UploadFragment)
            val edNIK = binding.edNIK.text.toString()
            val edNISN = binding.edNISN.text.toString()
            val edNPSN = binding.edNPSN.text.toString()
            val edName = binding.edfullname.text.toString()
            val edGender = binding.edGender.text.toString()
            val edTempatLahir = binding.edTempatLahir.text.toString()
            val edTglLahir = binding.edTglLahir.text.toString()
            val edMom = binding.edMom.text.toString()
            val edAddress = binding.edAddress.text.toString()
            val edPostCode = binding.edPosCode.text.toString()
            val edReligion = binding.edReligion.text.toString()
            val edCollege = binding.edCollege.text.toString()
            val radioPrestasi = binding.radioPrestasi.toString()
            val edUniv = binding.edUniv.text.toString()
            val edJurusan = binding.edJurusan.text.toString()
            val edJenjang = binding.edJenjang.text.toString()
            val edSemester = binding.edSemester.text.toString()
            val edUkt = binding.edUkt.text.toString()
            val edIpk = binding.edIpk.text.toString()
            val fotoRumah = getFile

            applyPresenter?.postData(
                edNIK.toInt(),
                edNISN.toInt(),
                edNPSN.toInt(),
                edName,
                edGender,
                edTempatLahir,
                edTglLahir,
                edMom,
                edAddress,
                edPostCode.toInt(),
                edReligion,
                edCollege,
                radioPrestasi.toBoolean(),
                edUniv,
                edJurusan,
                edJenjang,
                edSemester.toInt(),
                edUkt.toInt(),
                edIpk.toFloat(),
                fotoRumah!!
            )
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