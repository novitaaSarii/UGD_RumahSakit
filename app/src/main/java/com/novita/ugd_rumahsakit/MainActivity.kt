package com.novita.ugd_rumahsakit

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.core.os.bundleOf
import com.google.android.material.textfield.TextInputEditText

class MainActivity : AppCompatActivity() {
    private lateinit var username: TextInputEditText
    private lateinit var password: TextInputEditText
    private lateinit var email : TextInputEditText
    private lateinit var tanggalLahir: TextInputEditText
    private lateinit var nomorTelepon: TextInputEditText
    private lateinit var btnRegister : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        username = findViewById(R.id.etUsername)
        password = findViewById(R.id.etPassword)
        email = findViewById(R.id.etEmail)
        tanggalLahir = findViewById(R.id.etTanggalLahir)
        nomorTelepon = findViewById(R.id.etNomorTelepon)
        btnRegister = findViewById(R.id.btnRegister)

        btnRegister.setOnClickListener {
            val intent = Intent(this, NextActivity::class.java)
            val mBundle = Bundle()
            mBundle.putString("username", username.text.toString())
            mBundle.putString("password", password.text.toString())
            mBundle.putString("email", email.text.toString())
            mBundle.putString("tanggalLahir", tanggalLahir.text.toString())
            mBundle.putString("nomorTelepon", nomorTelepon.text.toString())
            intent.putExtra("register", mBundle)
            intent.putExtras(Bundle)

            startActivity(intent)
        }
    }
}