package com.marinato.listatelefonica.Activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.marinato.listatelefonica.R

class SplahScreen_Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splah_screen)

        Handler(Looper.getMainLooper()).postDelayed({
            startActivity(Intent(this, LoguinActivity::class.java))
        }, 1000)
    }
}