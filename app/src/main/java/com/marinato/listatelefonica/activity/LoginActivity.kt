package com.marinato.listatelefonica.activity

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.marinato.listatelefonica.R
import com.marinato.listatelefonica.database.DBHelper
import com.marinato.listatelefonica.databinding.ActivityLoguinBinding

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoguinBinding
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoguinBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val db = DBHelper(this)

        sharedPreferences = application.getSharedPreferences(
            "login", Context.MODE_PRIVATE)

        binding.buttonLogin.setOnClickListener { // mudar para MainActivity
            val username = binding.editUsername.text.toString()
            val password = binding.editPassword.text.toString()
            val logged = binding.checkboxLogged.isChecked

            if (username.isNotEmpty() && password.isNotEmpty()){
                if (db.login(username, password)){
                    if (logged){
                        val editor: SharedPreferences.Editor = sharedPreferences.edit()
                        editor.putString("username", username)
                        editor.apply()
                    }
                    startActivity(Intent(this, MainActivity::class.java))
                    finish()
                }else{
                    Toast.makeText(
                        applicationContext,
                        getString(R.string.login_error),
                        Toast.LENGTH_SHORT
                    ).show()
                    binding.editUsername.setText("")
                    binding.editPassword.setText("")
                }
            }else{
                Toast.makeText(
                    applicationContext,
                    getString(R.string.please_insert_all_required_fields),
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
        binding.textSignup.setOnClickListener { // mudar para SignupActivity
            startActivity(Intent(this, SignupActivity2::class.java))
        }
        binding.textRecoverPassword.setOnClickListener { }
    }
}