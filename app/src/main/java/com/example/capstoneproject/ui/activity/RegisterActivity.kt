package com.example.capstoneproject.ui.activity

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.capstoneproject.data.model.User
import com.example.capstoneproject.databinding.ActivityRegisterBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

class RegisterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = FirebaseAuth.getInstance()
        playAnimation()

        binding.haveAccount.setOnClickListener {
            intentLogin()
        }

        binding.buttonRegister.setOnClickListener {
            val username = binding.edName.text.toString()
            val email = binding.edEmail.text.toString()
            val password = binding.edtPass.text.toString()
            val phoneNumber = binding.edPhone.text.toString()

            // validasi username
            if (username.isEmpty()) {
                binding.edEmail.error = "Nama harus diisi!"
                binding.edEmail.requestFocus()
                return@setOnClickListener
            }
            // validasi email
            if (email.isEmpty()) {
                binding.edEmail.error = "Email harus diisi!"
                binding.edEmail.requestFocus()
                return@setOnClickListener
            }
            // validasi email valid
            if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                binding.edEmail.error = "Email tidak valid!"
                binding.edEmail.requestFocus()
                return@setOnClickListener
            }
            // validasi password
            if (password.isEmpty()) {
                binding.edPassword.error = "Password harus diisi!"
                binding.edPassword.requestFocus()
                return@setOnClickListener
            }
            // validasi panjang password
            if (password.length < 8) {
                binding.edPassword.error = "Password minimal 8 karakter!"
                binding.edPassword.requestFocus()
                return@setOnClickListener
            }

            auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        val user = User(username, phoneNumber, email, password)

                        FirebaseDatabase.getInstance("https://capstone-project-3480d-default-rtdb.firebaseio.com/")
                            .getReference("Users")
                            .child(FirebaseAuth.getInstance().currentUser?.uid!!)
                            .setValue(user)
                            .addOnCompleteListener { innerTask ->
                                if (innerTask.isSuccessful) {
                                    Toast.makeText(
                                        this@RegisterActivity,
                                        "Register Successful",
                                        Toast.LENGTH_LONG
                                    ).show()
                                    val moveLogin =
                                        Intent(applicationContext, LoginActivity::class.java)
                                    startActivity(moveLogin)
                                } else {
                                    Toast.makeText(
                                        this@RegisterActivity,
                                        "Register Failed",
                                        Toast.LENGTH_LONG
                                    ).show()
                                }
                            }
                    } else {
                        Toast.makeText(this@RegisterActivity, "Register Failed", Toast.LENGTH_LONG)
                            .show()
                    }
                }
        }

    }

    private fun intentLogin() {
        val i = Intent(this, LoginActivity::class.java)
        startActivity(i)
    }

    private fun playAnimation() {
        val registerText =
            ObjectAnimator.ofFloat(binding.registerText, View.ALPHA, 1f).setDuration(300)
        val unameText = ObjectAnimator.ofFloat(binding.userText, View.ALPHA, 1f).setDuration(300)
        val unameEditText = ObjectAnimator.ofFloat(binding.layName, View.ALPHA, 1f).setDuration(300)
        val phoneText = ObjectAnimator.ofFloat(binding.phoneNumber, View.ALPHA, 1f).setDuration(300)
        val phoneEditText =
            ObjectAnimator.ofFloat(binding.layPhone, View.ALPHA, 1f).setDuration(300)
        val emailText = ObjectAnimator.ofFloat(binding.layEmail, View.ALPHA, 1f).setDuration(300)
        val emailEditText =
            ObjectAnimator.ofFloat(binding.emailText, View.ALPHA, 1f).setDuration(300)
        val passText = ObjectAnimator.ofFloat(binding.passText, View.ALPHA, 1f).setDuration(300)
        val passEditText =
            ObjectAnimator.ofFloat(binding.edPassword, View.ALPHA, 1f).setDuration(300)
        val registerButton =
            ObjectAnimator.ofFloat(binding.buttonRegister, View.ALPHA, 1f).setDuration(300)
        val haveAccountText =
            ObjectAnimator.ofFloat(binding.haveAccount, View.ALPHA, 1f).setDuration(300)

        AnimatorSet().apply {
            playSequentially(
                registerText,
                unameText,
                unameEditText,
                phoneText,
                phoneEditText,
                emailText,
                emailEditText,
                passText,
                passEditText,
                registerButton,
                haveAccountText
            )
            duration = 400
            start()
        }
    }
}