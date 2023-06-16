package com.marinato.listatelefonica.Activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.marinato.listatelefonica.databinding.ActivitySignup2Binding

class SignupActivity2 : AppCompatActivity() {

    private lateinit var binding: ActivitySignup2Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignup2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonSignup.setOnClickListener {
            finish() // validar signup
        }

    }
}