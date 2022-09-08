package com.novita.ugd_rumahsakit

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.constraintlayout.widget.ConstraintLayout
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class MainActivity : AppCompatActivity() {
    private lateinit var Iusername: TextInputLayout
    private lateinit var Ipassword: TextInputLayout
    private lateinit var Iemail : TextInputLayout
    private lateinit var ItanggalLahir: TextInputLayout
    private lateinit var InomorTelepon: TextInputLayout
    private lateinit var IbtnRegister : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_createraccount)

        Iusername = findViewById(R.id.etUsername)
        Ipassword = findViewById(R.id.etPassword)
        Iemail = findViewById(R.id.etEmail)
        ItanggalLahir = findViewById(R.id.etTanggalLahir)
        InomorTelepon = findViewById(R.id.etNomorTelepon)
        IbtnRegister = findViewById(R.id.btn_create)


        IbtnRegister.setOnClickListener {
            var checkCreateAccount = false
            val username : String =  Iusername.editText.toString()
            val password : String =  Ipassword.editText.toString()
            val email  : String =  Iemail.editText.toString()
            val tanggalLahir : String =  ItanggalLahir.editText.toString()
            val nomorTelepon : String =  InomorTelepon.editText.toString()


            if(username.isEmpty()){
                Iusername.setError("Username wrong")
                checkCreateAccount = false
            }

            if(password.isEmpty()){
                Ipassword.setError("password wrong")
                checkCreateAccount = false
            }

            if(email.isEmpty()){
                Iemail.setError("email wrong")
                checkCreateAccount = false
            }

            if(tanggalLahir.isEmpty()){
                ItanggalLahir.setError("Tanggal  Lahir wrong ")
                checkCreateAccount = false
            }

            if(nomorTelepon.isEmpty()){
                InomorTelepon.setError("nomor Telepon wrong")
                checkCreateAccount = false
            }

            val balikLogin = Intent(this, Tampilan::class.java)

            val mBundle = Bundle()
            mBundle.putString("username", Iusername.editText.toString())
            mBundle.putString("password", Ipassword.editText.toString())
            mBundle.putString("email", Iemail.editText.toString())
            mBundle.putString("tanggalLahir", ItanggalLahir.editText.toString())
            mBundle.putString("nomorTelepon", InomorTelepon.editText.toString())
            intent.putExtra("register", mBundle)

            if(mBundle !=null){
                intent.putExtras(mBundle)
            }
            startActivity(balikLogin)

        }




    }




}