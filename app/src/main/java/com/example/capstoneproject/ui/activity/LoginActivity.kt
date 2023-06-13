package com.example.capstoneproject.ui.activity

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.capstoneproject.data.model.User
import com.example.capstoneproject.data.utils.Preferences
import com.example.capstoneproject.databinding.ActivityLoginBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.google.firebase.database.ktx.getValue

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private lateinit var auth: FirebaseAuth
    private val database: DatabaseReference = FirebaseDatabase.getInstance().reference
    private lateinit var context: Context
    private lateinit var pref: Preferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        context = this
        pref = Preferences(context)
        auth = FirebaseAuth.getInstance()
        playAnimation()


        binding.newAccount.setOnClickListener {
            val i = Intent(this, RegisterActivity::class.java)
            startActivity(i)
        }

        binding.buttonLogin.setOnClickListener {
            val email = binding.edEmail.text.toString()
            val password = binding.edtPass.text.toString()

            // validasi email
            if (email.isEmpty()) {
                binding.edEmail.error = "Email harus diisi!"
                binding.edEmail.requestFocus()
                return@setOnClickListener
            } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                // validasi email valid
                binding.edEmail.error = "Email tidak valid!"
                binding.edEmail.requestFocus()
                return@setOnClickListener
            } else if (password.isEmpty()) {
                // validasi password
                binding.edPassword.error = "Password harus diisi!"
                binding.edPassword.requestFocus()
                return@setOnClickListener
            } else {
                val query: Query = database.child("Users").orderByChild("email").equalTo(email)
                query.addListenerForSingleValueEvent(object : ValueEventListener {
                    override fun onDataChange(snapshot: DataSnapshot) {
                        if (snapshot.exists()) {
                            for (item in snapshot.children) {
                                val user = item.getValue<User>()
                                if (user != null) {
                                    if (user.password == password) {
                                        pref.prefStatus = true
                                        pref.prefName = user.name
                                        val intent = if (user.name.equals(user.name)) {
                                            Intent(context, MainActivity::class.java)
                                        } else {
                                            Intent(context, MainActivity::class.java)
                                        }
                                        startActivity(intent)
                                        finish()
                                    } else {
                                        Toast.makeText(
                                            context,
                                            "Kata sandi belum sesuai!",
                                            Toast.LENGTH_LONG
                                        ).show()
                                    }
                                }
                            }
                        } else {
                            Toast.makeText(context, "Email belum terdaftar", Toast.LENGTH_LONG)
                                .show()
                        }
                    }

                    override fun onCancelled(error: DatabaseError) {
                        Toast.makeText(context, error.message, Toast.LENGTH_LONG).show()
                    }

                })
            }
        }
    }

    override fun onStart() {
        super.onStart()
        if (pref.prefStatus) {
            val intent = if (pref.prefName.equals(pref.prefName)) {
                Intent(context, MainActivity::class.java)
            } else {
                Intent(context, MainActivity::class.java)
            }
            startActivity(intent)
            finish()
        }
    }

    private fun playAnimation() {
        ObjectAnimator.ofFloat(binding.imageView, View.TRANSLATION_X, -30f, 30f).apply {
            duration = 6000
            repeatCount = ObjectAnimator.INFINITE
            repeatMode = ObjectAnimator.REVERSE
        }.start()

        val loginText = ObjectAnimator.ofFloat(binding.loginText, View.ALPHA, 1f).setDuration(300)
        val emailText = ObjectAnimator.ofFloat(binding.emailText, View.ALPHA, 1f).setDuration(300)
        val emailEditText =
            ObjectAnimator.ofFloat(binding.layEmail, View.ALPHA, 1f).setDuration(300)
        val passText = ObjectAnimator.ofFloat(binding.passText, View.ALPHA, 1f).setDuration(300)
        val passEditText =
            ObjectAnimator.ofFloat(binding.edPassword, View.ALPHA, 1f).setDuration(300)
        val loginButton =
            ObjectAnimator.ofFloat(binding.buttonLogin, View.ALPHA, 1f).setDuration(300)
        val newAccountText =
            ObjectAnimator.ofFloat(binding.newAccount, View.ALPHA, 1f).setDuration(300)

        AnimatorSet().apply {
            playSequentially(
                loginText,
                emailText,
                emailEditText,
                passText,
                passEditText,
                loginButton,
                newAccountText
            )
            duration = 400
            start()
        }
    }

}