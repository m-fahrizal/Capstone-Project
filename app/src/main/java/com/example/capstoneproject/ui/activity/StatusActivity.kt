package com.example.capstoneproject.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.view.MenuItem
import com.example.capstoneproject.R
import com.example.capstoneproject.databinding.ActivityStatusBinding
import com.example.capstoneproject.ui.fragment.EligibleFragment
import com.example.capstoneproject.ui.fragment.NotEligibleFragment
import com.example.capstoneproject.ui.fragment.ProcessFragment
import java.io.File

class StatusActivity : AppCompatActivity() {
    private lateinit var binding: ActivityStatusBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityStatusBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = getString(R.string.status)

        val predictionFile = File(this.filesDir.toString() + "mykip.txt")
//        println(predictionFile.reader().readLines().get(0))

        val prediction = predictionFile.reader().readLines().get(0)

        when (prediction) {
            "Eligible" -> {
                intentEligible()
            }
            "Not Eligible" -> {
                intentNotEligible()
            }
            else -> {
                intentProcess()
            }
        }
    }

    override fun onOptionsItemSelected(back: MenuItem): Boolean {
        when (back.itemId) {
            android.R.id.home -> {
                finish()
            }
        }
        return super.onOptionsItemSelected(back)
    }

    private fun intentEligible(){
        val fragment = EligibleFragment() // Ganti "MyFragment" dengan nama Fragment Anda
        val fragmentManager = supportFragmentManager // Gunakan fragmentManager untuk AndroidX, atau supportFragmentManager untuk versi yang lebih lama
        val transaction = fragmentManager.beginTransaction()
        transaction.replace(R.id.status_layout, fragment) // Ganti "R.id.fragment_container" dengan ID container Fragment di layout Anda
        transaction.commit()
    }

    private fun intentNotEligible(){
        val fragment = NotEligibleFragment() // Ganti "MyFragment" dengan nama Fragment Anda
        val fragmentManager = supportFragmentManager // Gunakan fragmentManager untuk AndroidX, atau supportFragmentManager untuk versi yang lebih lama
        val transaction = fragmentManager.beginTransaction()
        transaction.replace(R.id.status_layout, fragment) // Ganti "R.id.fragment_container" dengan ID container Fragment di layout Anda
        transaction.commit()
    }

    private fun intentProcess(){
        val fragment = ProcessFragment() // Ganti "MyFragment" dengan nama Fragment Anda
        val fragmentManager = supportFragmentManager // Gunakan fragmentManager untuk AndroidX, atau supportFragmentManager untuk versi yang lebih lama
        val transaction = fragmentManager.beginTransaction()
        transaction.replace(R.id.status_layout, fragment) // Ganti "R.id.fragment_container" dengan ID container Fragment di layout Anda
        transaction.commit()
    }
}