package com.marinato.listatelefonica.activity

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.marinato.listatelefonica.R
import com.marinato.listatelefonica.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val sharedPreferences = application.getSharedPreferences(
            "login", Context.MODE_PRIVATE)

        binding.buttonLogout.setOnClickListener {
            val editor: SharedPreferences.Editor = sharedPreferences.edit()
            editor.putString("username", "")
            editor.apply()
            finish()
        }
    }
}
