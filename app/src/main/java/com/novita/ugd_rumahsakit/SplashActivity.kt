package com.novita.ugd_rumahsakit

import android.app.ActionBar
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import android.view.WindowManager;


class splashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        Handler(Looper.getMainLooper()).postDelayed({
           startActivity(Intent(this@splashActivity, MainActivity::class.java))
            finish()
        },3000)
    }
}