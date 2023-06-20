package com.marinato.listatelefonica.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.marinato.listatelefonica.R
import com.marinato.listatelefonica.databinding.ActivityNewContactBinding

class NewContactActivity : AppCompatActivity() {

    private lateinit var binding: ActivityNewContactBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNewContactBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}