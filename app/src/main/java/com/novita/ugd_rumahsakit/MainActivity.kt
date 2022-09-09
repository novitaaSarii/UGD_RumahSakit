package com.novita.ugd_rumahsakit

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.constraintlayout.widget.ConstraintLayout
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class MainActivity : AppCompatActivity() {
    private lateinit var Iusername: TextInputEditText
    private lateinit var Ipassword: TextInputEditText
    private lateinit var Iemail : TextInputEditText
    private lateinit var ItanggalLahir: TextInputEditText
    private lateinit var InomorTelepon: TextInputEditText
    private lateinit var IbtnRegister : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_createraccount)

        Iusername = findViewById(R.id.username)
        Ipassword = findViewById(R.id.password)
        Iemail = findViewById(R.id.email)
        ItanggalLahir = findViewById(R.id.tanggal)
        InomorTelepon = findViewById(R.id.Nomor)
        IbtnRegister = findViewById(R.id.btn_create)


        IbtnRegister.setOnClickListener {
            var checkCreateAccount = false

            val username : String =  Iusername.text.toString()
            val password : String =  Ipassword.text.toString()
            val email  : String =  Iemail.text.toString()
            val tanggalLahir : String =  ItanggalLahir.text.toString()
            val nomorTelepon : String =  InomorTelepon.text.toString()

            if(username.isEmpty()){
                Iusername.setError("Username wrong")
                checkCreateAccount = false
                return@setOnClickListener
            }

            if(password.isEmpty()){
                Ipassword.setError("password wrong")
                checkCreateAccount = false
                return@setOnClickListener
            }

            if(email.isEmpty()){
                Iemail.setError("email wrong")
                checkCreateAccount = false
                return@setOnClickListener
            }

            if(tanggalLahir.isEmpty()){
                ItanggalLahir.setError("Tanggal Lahir wrong ")
                checkCreateAccount = false
                return@setOnClickListener
            }

            if(nomorTelepon.isEmpty()){
                InomorTelepon.setError("nomor Telepon wrong")
                checkCreateAccount = false
                return@setOnClickListener
            }

            val balikLogin = Intent(this, Tampilan::class.java)
            val mBundle = Bundle()
            mBundle.putString("username", Iusername.text.toString())
            mBundle.putString("password", Ipassword.text.toString())
            mBundle.putString("email", Iemail.text.toString())
            mBundle.putString("tanggalLahir", ItanggalLahir.text.toString())
            mBundle.putString("nomorTelepon", InomorTelepon.text.toString())
            balikLogin.putExtra("register", mBundle)

            startActivity(balikLogin)

        }


    }




}