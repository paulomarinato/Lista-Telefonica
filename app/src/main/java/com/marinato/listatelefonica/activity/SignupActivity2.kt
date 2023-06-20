package com.marinato.listatelefonica.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.marinato.listatelefonica.R
import com.marinato.listatelefonica.database.DBHelper
import com.marinato.listatelefonica.databinding.ActivitySignup2Binding

class SignupActivity2 : AppCompatActivity() {

    private lateinit var binding: ActivitySignup2Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignup2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        val db = DBHelper(this)

        binding.buttonSignup.setOnClickListener {// validar signup
            val username = binding.editUsername.text.toString()
            val password = binding.editPassword.text.toString()
            val passwordConfirm = binding.editConfirmPassword.text.toString()

            if (username.isNotEmpty() && password.isNotEmpty() && passwordConfirm.isNotEmpty()) {
                if (password.equals(passwordConfirm)){
                    val res = db.insertuser(username, password)
                    if (res > 0) {
                        Toast.makeText(applicationContext,
                            getString(R.string.signup_ok),
                            Toast.LENGTH_SHORT
                        ).show()
                        finish()
                    } else {
                        Toast.makeText(applicationContext,
                            getString(R.string.signup_error),
                            Toast.LENGTH_SHORT
                        ).show()
                        binding.editUsername.setText("")
                        binding.editPassword.setText("")
                        binding.editConfirmPassword.setText("")
                    }
                }else {
                        Toast.makeText(
                            applicationContext,
                            getString(R.string.password_don_t_match),
                            Toast.LENGTH_SHORT
                        ).show()
                    }
            } else {
                Toast.makeText(
                    applicationContext,
                    getString(R.string.please_insert_all_required_fields),
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }
}
