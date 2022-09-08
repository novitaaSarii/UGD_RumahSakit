package com.novita.ugd_rumahsakit

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.constraintlayout.widget.ConstraintLayout
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class Tampilan : AppCompatActivity() {
    private lateinit var inputUsername: TextInputLayout
    private lateinit var inputpassword: TextInputLayout
    private lateinit var tampilanlayout: ConstraintLayout


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tampilan)

        setTitle("UGD2_RumahSakit")

        inputUsername= findViewById(R.id.inputLayoutUsername)
        inputpassword=findViewById(R.id.inputLayoutPassword)
        tampilanlayout=findViewById(R.id.tampilanLayout)
        val btnRegister : Button = findViewById(R.id.btnRegister)
        val btnLogin : Button = findViewById(R.id.btnLogin)

        btnLogin.setOnClickListener{
            var checkLogin = false
            val username: String = inputUsername.editText.toString()
            val password: String = inputpassword.editText.toString()

            if(username.isEmpty()){
                inputUsername.setError("Username wrong")
                checkLogin = false
            }

            if(password.isEmpty()){
                inputpassword.setError("Password Wrong")
                checkLogin=false
            }

            if(username=="" && password =="") checkLogin = true
            if(!checkLogin) return@setOnClickListener
            val moveHome = Intent(this@Tampilan, HomeActivity::class.java)
            startActivity(moveHome)
        }

        btnRegister.setOnClickListener{

            val moveMain = Intent(this@Tampilan, MainActivity::class.java)
            startActivity(moveMain)
        }

    }

}

