package com.novita.ugd_rumahsakit

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.get
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.master.permissionhelper.PermissionHelper
import com.novita.ugd_rumahsakit.MVVM.registerAdapter
import com.novita.ugd_rumahsakit.room.registerDB
import com.shashank.sony.fancytoastlib.FancyToast

class Tampilan : AppCompatActivity() {
    val db by lazy{ registerDB(this) }
    lateinit var registerAdapter: registerAdapter

    private lateinit var inputUsername: TextInputLayout
    private lateinit var inputpassword: TextInputLayout
    private lateinit var tampilanlayout: ConstraintLayout

    lateinit var Register: Bundle
    lateinit var vUsername: String
    lateinit var vPassword: String

    var sharePreferences: SharedPreferences? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tampilan)

        //untuk permisi buka izin
        val permissionHelper = PermissionHelper(this, arrayOf(Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE), 100)
        //Request all permission
        permissionHelper?.requestAll {
            Log.d("Permision", "All permission granted")
        }

        setTitle("UGD2_RumahSakit")

        inputUsername= findViewById(R.id.inputLayoutUsername)
        inputpassword=findViewById(R.id.inputLayoutPassword)
        tampilanlayout=findViewById(R.id.tampilanLayout)
        val btnRegister : Button = findViewById(R.id.btnRegister)
        val btnLogin : Button = findViewById(R.id.btnLogin)

        sharePreferences = getSharedPreferences("myPerference", Context.MODE_PRIVATE)

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
                FancyToast.makeText(this,"Data Tidak ada atau Data Salah!",FancyToast.LENGTH_LONG,FancyToast.ERROR,true).show()
                inputUsername.setError("Username Salah")
                inputpassword.setError("Password Salah ")
                return@OnClickListener
            }
            else{
                Log.d("bundle",extra.toString())
                vUsername = extra.getString("username")!!
                vPassword = extra.getString("password")!!

            }

            if(username == vUsername  && password == vPassword) checkLogin = true
            if(!checkLogin) return@OnClickListener

            /*
              share preference untuk membandingkan username waktu login
            */

            val editor: SharedPreferences.Editor = sharePreferences!!.edit()
            editor.putString("username", username)
            editor.apply()
            FancyToast.makeText(this,"Selamat Berhasil Login ",FancyToast.LENGTH_LONG,FancyToast.SUCCESS,true).show()
            val moveHome = Intent(this@Tampilan, HomeActivity::class.java)
            startActivity(moveHome)
        })

        btnRegister.setOnClickListener{

            val moveMain = Intent(this@Tampilan, MainActivity::class.java)
            startActivity(moveMain)
        }

    }

}

