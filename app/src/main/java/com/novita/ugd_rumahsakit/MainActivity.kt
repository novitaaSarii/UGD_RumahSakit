package com.novita.ugd_rumahsakit

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.novita.ugd_rumahsakit.MVVM.registerAdapter
import com.novita.ugd_rumahsakit.MainAdapter.CreateAccountAdapter
import com.novita.ugd_rumahsakit.Task.TaskList
import com.novita.ugd_rumahsakit.databinding.ActivityCreateraccountBinding
import com.novita.ugd_rumahsakit.room.register
import com.novita.ugd_rumahsakit.room.registerDB
import kotlinx.android.synthetic.main.activity_createraccount.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    /*
         View Binding
      */
    var binding: ActivityCreateraccountBinding? = null

    /*
        create data base
     */
    val db by lazy { registerDB(this) }
    lateinit var registerAdapter: registerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_createraccount)

        /*
            View Binding
        */
        binding = ActivityCreateraccountBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        val adapter = CreateAccountAdapter(TaskList.tasklist)


        binding?.btnCreate?.setOnClickListener {
            var checkCreateAccount = false

            val username : String =  binding?.etUsername?.editText?.text.toString()
            val password : String =  binding?.etPassword?.editText?.text.toString()
            val email  : String =  binding?.etEmail?.editText?.text.toString()
            val tanggalLahir : String =  binding?.etTanggalLahir?.editText?.text.toString()
            val nomorTelepon : String =  binding?.etNomorTelepon?.editText?.text.toString()

            if(username.isEmpty()){
                etUsername.setError("Username wrong")
                checkCreateAccount = false
                return@setOnClickListener
            }

            if(password.isEmpty()){
                etPassword.setError("password wrong")
                checkCreateAccount = false
                return@setOnClickListener
            }

            if(email.isEmpty()){
                etEmail.setError("email wrong")
                checkCreateAccount = false
                return@setOnClickListener
            }

            if(tanggalLahir.isEmpty()){
                etTanggalLahir.setError("Tanggal Lahir wrong ")
                checkCreateAccount = false
                return@setOnClickListener
            }

            if(nomorTelepon.isEmpty()){
                etNomorTelepon.setError("nomor Telepon wrong")
                checkCreateAccount = false
                return@setOnClickListener
            }

            CoroutineScope(Dispatchers.IO).launch {
                db.registerDAO().addregister(
                    register(
                        0,
                        etUsername?.editText?.text.toString(),
                        etPassword?.editText?.text.toString(),
                        etEmail?.editText?.text.toString(),
                        etTanggalLahir?.editText?.text.toString(),
                        etNomorTelepon?.editText?.text.toString()
                    )
                )}

            val balikLogin = Intent(this, Tampilan::class.java)
            val mBundle = Bundle()
            mBundle.putString("username", etUsername?.editText?.text.toString())
            mBundle.putString("password", etPassword?.editText?.text.toString() )
            mBundle.putString("email", etEmail?.editText?.text.toString())
            mBundle.putString("tanggalLahir", etTanggalLahir?.editText?.text.toString())
            mBundle.putString("nomorTelepon", etNomorTelepon?.editText?.text.toString())
            balikLogin.putExtra("register", mBundle)

            startActivity(balikLogin)

        }

        fun intentCreate(registerusername : String, intentType: String){
            startActivity(
                Intent(applicationContext, MainActivity::class.java)
                    .putExtra("intent_username", registerusername)
                    .putExtra("intent_type", intentType)
            )
        }
    }

}



