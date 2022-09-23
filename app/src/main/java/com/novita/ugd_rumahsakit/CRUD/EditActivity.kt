package com.novita.ugd_rumahsakit.CRUD


import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.novita.ugd_rumahsakit.RoomDokter.Constant
import com.novita.ugd_rumahsakit.RoomDokter.Dokter
import com.novita.ugd_rumahsakit.RoomDokter.DokterDB
import kotlinx.android.synthetic.main.activity_edit_dokter.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class EditActivity : AppCompatActivity() {
    val db by lazy { DokterDB(this) }
    private var namaDokter: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit)
        setupView()
        setupListener()


    }
    fun setupView(){
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        val intentType = intent.getIntExtra("intent_type", 0)
        when (intentType){
            Constant.TYPE_CREATE -> {
                button_update.visibility = View.GONE
            }
            Constant.TYPE_READ -> {
                button_save.visibility = View.GONE
                button_update.visibility = View.GONE
                getDokter()
            }
            Constant.TYPE_UPDATE -> {
                button_save.visibility = View.GONE
                getDokter()
            }
        }
    }
    private fun setupListener() {
        button_save.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
                db.DokterDAO().addDokter(
                    Dokter(0,edit_namaDokter.text.toString(), edit_IDDokter.text.toString(),
                        edit_spesialis.text.toString(), edit_kontakDokter.text.toString())
                )
                finish()
            }
        }
        button_update.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
                db.DokterDAO().updateDokter(
                    Dokter(namaDokter = , edit_namaDokter.text.toString(),
                        edit_namaDokter.text.toString())
                )
                finish()
            }
        }
    }

    fun getDokter() {
        namaDokter = intent.getIntExtra("intent_namaDokter", 0)
        CoroutineScope(Dispatchers.IO).launch {
            val dokter = db.DokterDAO().getDokter(namaDokter)[0]
            edit_namaDokter.setText(dokter.namaDokter)
            edit_IDDokter.setText(dokter.IDDokter)
            edit_spesialis.setText(dokter.spesialis)
            edit_kontakDokter.setTest(dokter.kontakDokter)
        }
    }
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }

}