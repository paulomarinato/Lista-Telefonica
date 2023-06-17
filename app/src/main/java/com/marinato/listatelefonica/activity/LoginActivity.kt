package com.marinato.listatelefonica.activity

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

        binding.buttonLogin.setOnClickListener { // mudar para MainActivity
            startActivity(Intent(this, MainActivity::class.java))

        }
        binding.textSignup.setOnClickListener { // mudar para SignupActivity
            startActivity(Intent(this, SignupActivity2::class.java))
        }

        binding.textRecoverPassword.setOnClickListener { }
    }
}