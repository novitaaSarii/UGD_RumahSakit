package com.novita.ugd_rumahsakit


import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContentProviderCompat
import com.novita.ugd_rumahsakit.MVVM.registerAdapter
import com.novita.ugd_rumahsakit.camera.cameraActivity
import com.novita.ugd_rumahsakit.databinding.ActivityEditBinding
import com.novita.ugd_rumahsakit.room.register
import com.novita.ugd_rumahsakit.room.registerDB
import kotlinx.android.synthetic.main.activity_profil.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class EditActivity : AppCompatActivity() {
    /*
        View Binding
     */
    val db by lazy { registerDB(this) }
    lateinit var registerAdapter: registerAdapter
    var binding: ActivityEditBinding? = null
    var sharePreferences: SharedPreferences? = null

    lateinit var update: Bundle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharePreferences = getSharedPreferences("myPerference", Context.MODE_PRIVATE)
        val strName = sharePreferences!!.getString("username", "")!!

        binding = ActivityEditBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        val btnImage= binding?.imageView

        btnImage?.setOnClickListener(){
            val mainHome= Intent(this@EditActivity, cameraActivity::class.java)
            this.startActivity(mainHome)
        }

        update= intent.extras!!
        binding?.namauser?.setText(update.getString("username"))
        binding?.namapass?.setText(update.getString("password"))
        binding?.namaemail?.setText(update.getString("email"))
        binding?.namatanggal?.setText(update.getString("tanggal"))
        binding?.namahp?.setText(update.getString("nomor"))


        binding?.buttonUpdate?.setOnClickListener {
            var checkUpdate = false
            val username: String = binding?.namauser?.text.toString()
            val password: String = binding?.namapass?.text.toString()
            val email: String = binding?.namaemail?.text.toString()
            val tanggalLahir: String = binding?.namatanggal?.text.toString()
            val nomorTelepon: String = binding?.namahp?.text.toString()

            CoroutineScope(Dispatchers.IO).launch {

                val username: register = db.registerDAO().getregister(strName).first()

                db.registerDAO().updateregister(
                    register(
                        username.id,
                        namauser?.text.toString(),
                        namapass?.text.toString(),
                        namaemail?.text.toString(),
                        namatanggal?.text.toString(),
                        namahp?.text.toString()

                    )
                )
                val update = Intent(this@EditActivity, ProfilActivity::class.java)
                startActivity(update)
            }

        }

    }
}