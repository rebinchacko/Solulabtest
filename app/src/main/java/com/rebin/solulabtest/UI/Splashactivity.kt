package com.rebin.solulabtest.UI

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.rebin.solulabtest.R

class Splashactivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splashactivity)
        naviagtionToRegistration()
    }

    private fun naviagtionToRegistration() {
        val handler = Handler()
        handler.postDelayed(Runnable {
                var intent= Intent(this,MainActivity::class.java)
                startActivity(intent)
                finish()

        }, 2000)
    }
}