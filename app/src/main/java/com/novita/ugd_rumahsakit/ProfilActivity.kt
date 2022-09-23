package com.novita.ugd_rumahsakit

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.novita.ugd_rumahsakit.MVVM.registerAdapter
import com.novita.ugd_rumahsakit.databinding.ActivityCreateraccountBinding
import com.novita.ugd_rumahsakit.databinding.ActivityProfilBinding
import com.novita.ugd_rumahsakit.room.register
import com.novita.ugd_rumahsakit.room.registerDB
import kotlinx.android.synthetic.main.activity_createraccount.*
import kotlinx.android.synthetic.main.activity_createraccount.password
import kotlinx.android.synthetic.main.activity_profil.*
import kotlinx.android.synthetic.main.activity_profil.view.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ProfilActivity : AppCompatActivity() {

    val db by lazy { registerDB(this) }
    lateinit var registerAdapter: registerAdapter
    var sharePreferences: SharedPreferences? = null
    var binding: ActivityProfilBinding? = null



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityProfilBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        CoroutineScope(Dispatchers.IO).launch {
            sharePreferences = getSharedPreferences("myPerference", Context.MODE_PRIVATE)
            val strName = sharePreferences!!.getString("username","")!!
            val username: register = db.registerDAO().getregister(strName).first()
            binding?.namauser?.text = username.username
            binding?.namapass?.text = username.password
            binding?.namaemail?.text = username.email
            binding?.namatanggal?.text = username.tanggal
            binding?.namahp?.text = username.nomor
        }
    }
}