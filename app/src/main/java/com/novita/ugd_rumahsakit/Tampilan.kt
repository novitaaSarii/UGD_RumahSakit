package com.novita.ugd_rumahsakit

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.get
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class Tampilan : AppCompatActivity() {
    private lateinit var inputUsername: TextInputLayout
    private lateinit var inputpassword: TextInputLayout
    private lateinit var tampilanlayout: ConstraintLayout

    lateinit var Register: Bundle
    lateinit var vUsername: String
    lateinit var vPassword: String

    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tampilan)

        setTitle("UGD2_RumahSakit")

        inputUsername= findViewById(R.id.inputLayoutUsername)
        inputpassword=findViewById(R.id.inputLayoutPassword)
        tampilanlayout=findViewById(R.id.tampilanLayout)
        val btnRegister : Button = findViewById(R.id.btnRegister)
        val btnLogin : Button = findViewById(R.id.btnLogin)

        btnLogin.setOnClickListener(View.OnClickListener{
            var checkLogin = false
            val username: String = inputUsername.getEditText()?.getText().toString()
            val password: String = inputpassword.getEditText()?.getText().toString()

            if(username.isEmpty()){
                inputUsername.setError("Username wrong")
                checkLogin = false
                return@OnClickListener
            }

            if(password.isEmpty()){
                inputpassword.setError("Password Wrong")
                checkLogin=false
                return@OnClickListener
            }
            val extra : Bundle? = getIntent().getBundleExtra("register")
            if (extra == null){
                inputUsername.setError("Silahkan daftar dahulu")
                return@OnClickListener
            }
            else{
                Log.d("bundle",extra.toString())
                vUsername = extra.getString("username")!!
                vPassword = extra.getString("password")!!

            }

            if(username == vUsername  && password == vPassword)  checkLogin = true
            if(!checkLogin) return@OnClickListener

            val moveHome = Intent(this@Tampilan, HomeActivity::class.java)
            startActivity(moveHome)
        })

        btnRegister.setOnClickListener{

            val moveMain = Intent(this@Tampilan, MainActivity::class.java)
            startActivity(moveMain)
        }

    }

}

