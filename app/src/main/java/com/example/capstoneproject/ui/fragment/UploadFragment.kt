package com.example.capstoneproject.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.capstoneproject.R
import com.example.capstoneproject.databinding.FragmentUploadBinding

class UploadFragment : Fragment() {
    private lateinit var binding: FragmentUploadBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentUploadBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.lyLihatStatus.setOnClickListener {
            toProfile()
        }
        binding.lyBackHome.setOnClickListener {
            toHome()
        }
    }

    private fun toHome() {
        val navController = findNavController()
        navController.navigate(R.id.action_navigation_upload_to_navigation_home)
    }

    private fun toProfile() {
        val navController = findNavController()
        navController.navigate(R.id.action_navigation_upload_to_navigation_profile)
    }
}