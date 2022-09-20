package com.novita.ugd_rumahsakit

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity


class splashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }
}