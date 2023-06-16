package com.marinato.listatelefonica.Activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.marinato.listatelefonica.databinding.ActivityLoguinBinding

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoguinBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoguinBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonLogin.setOnClickListener { }
        binding.textSignup.setOnClickListener {
            startActivity(Intent(this, SignupActivity2::class.java))
        } // mudar para Activity Signup

        binding.textRecoverPassword.setOnClickListener { }
    }
}