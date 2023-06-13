package com.example.capstoneproject.ui.fragment

import android.animation.ObjectAnimator
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.capstoneproject.data.model.User
import com.example.capstoneproject.data.utils.Preferences
import com.example.capstoneproject.databinding.FragmentProfileBinding
import com.example.capstoneproject.ui.activity.FaqActivity
import com.example.capstoneproject.ui.activity.GuideActivity
import com.example.capstoneproject.ui.activity.LoginActivity
import com.example.capstoneproject.ui.activity.StatusActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*

class ProfileFragment : Fragment() {
    private lateinit var binding: FragmentProfileBinding
    private lateinit var reference: DatabaseReference
    private lateinit var userID: String
    private lateinit var context: Context
    private lateinit var pref: Preferences
    private lateinit var name: String
    private lateinit var greeting: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        context = requireContext()
        pref = Preferences(context)
        playAnimation()

        val lyLogout = binding.lyButton
        lyLogout.setOnClickListener {
            logout()
        }
        val user = FirebaseAuth.getInstance().currentUser
        name = ""
        greeting = binding.username

        if (user != null) {
            userID = user.uid
            reference =
                FirebaseDatabase.getInstance(com.example.capstoneproject.BuildConfig.Auth_URL)
                    .getReference("Users")
                    .child(userID)

            reference.addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    val userGreeting = snapshot.getValue(User::class.java)

                    if (userGreeting != null) {
                        name = userGreeting.name
                        greeting.text = name
                    }
                }

                override fun onCancelled(error: DatabaseError) {
                    Toast.makeText(activity, "Something went wrong", Toast.LENGTH_LONG).show()
                }
            })
        }

        val lyPanduan = binding.lyPanduan
        lyPanduan.setOnClickListener {
            val i = Intent(requireContext(), GuideActivity::class.java)
            startActivity(i)
        }

        val lyStatus = binding.lyStatus
        lyStatus.setOnClickListener {
            intentStatus()
        }

        val lyFaq = binding.lyFAQ
        lyFaq.setOnClickListener {
            val i = Intent(requireContext(), FaqActivity::class.java)
            startActivity(i)
        }
    }

    private fun logout() {
        pref.prefClear()
        val intent = Intent(requireContext(), LoginActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(intent)
        activity?.finish()
    }

    private fun intentStatus() {
        val intent = Intent(requireContext(), StatusActivity::class.java)
        startActivity(intent)
    }

    private fun playAnimation() {
        ObjectAnimator.ofFloat(binding.imageView, View.TRANSLATION_X, -30f, 30f).apply {
            duration = 6000
            repeatCount = ObjectAnimator.INFINITE
            repeatMode = ObjectAnimator.REVERSE
        }.start()
    }
}