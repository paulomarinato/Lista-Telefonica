package com.marinato.listatelefonica

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper

class SplahScreen_Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splah_screen)

        Handler(Looper.getMainLooper()).postDelayed({
            startActivity(Intent(this, LoguinActivity::class.java))
        }, 1000)
    }
}