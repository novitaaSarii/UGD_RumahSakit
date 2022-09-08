package com.novita.ugd_rumahsakit

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.constraintlayout.widget.ConstraintLayout
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class MainActivity : AppCompatActivity() {
    private lateinit var username: TextInputEditText
    private lateinit var password: TextInputEditText
    private lateinit var email : TextInputEditText
    private lateinit var tanggalLahir: TextInputEditText
    private lateinit var nomorTelepon: TextInputEditText
    private lateinit var btnRegister : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_createraccount)

        username = findViewById(R.id.etUsername)
        password = findViewById(R.id.etPassword)
        email = findViewById(R.id.etEmail)
        tanggalLahir = findViewById(R.id.etTanggalLahir)
        nomorTelepon = findViewById(R.id.etNomorTelepon)
        btnRegister = findViewById(R.id.btn_create)

        btnRegister.setOnClickListener {
            val intent = Intent(this, Tampilan::class.java)
            val mBundle = Bundle()
            mBundle.putString("username", username.text.toString())
            mBundle.putString("password", password.text.toString())
            mBundle.putString("email", email.text.toString())
            mBundle.putString("tanggalLahir", tanggalLahir.text.toString())
            mBundle.putString("nomorTelepon", nomorTelepon.text.toString())
            intent.putExtra("register", mBundle)

            startActivity(intent)
            //EXCEPTION HANDLING LOGIN
            //private latenit var inputUsername: TextInputLayout
            //private latenit var inputPassword: TextInputLayout
            //private latenit var mainLayout: ConstraintLayout

            // btnLogin.setOnClickListener(View.OnClickListener {
                //var checkLogin = false
                //val username: String = inputUsername.getEditText()?.getText().toString()
                //val password: String = inputPassword.getEditText()?.getText().toString()

                //if (username.isEmpty()) {
                    //inputUsername.setError("Username must be filled with text")
                    //checkLogin = false
                //}

                //if (password.isEmpty()) {
                    //inputPassword.setError("Password must be filled with text")
                    //checkLogin = false
                //}

                //if (username == "username" && password == "admin") checkLogin = true
                //if (!checkLogin) return@OnClickListener
                //val moveHome = Intent(this@MainActivity, HomeActivity::class.java)
                //startActivity(moveHome)
            //})
        }
    }
}