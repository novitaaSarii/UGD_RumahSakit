package com.novita.ugd_rumahsakit.qrcode

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.novita.ugd_rumahsakit.R
import com.novita.ugd_rumahsakit.databinding.ActivityTampilanlibaryBinding

class tampilanlibary : AppCompatActivity(), View.OnClickListener  {
    private lateinit var binding: ActivityTampilanlibaryBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTampilanlibaryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonPdf.setOnClickListener(this)
        binding.buttonScanqr.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.button_pdf -> {
                intent = Intent(applicationContext, pdfactivity::class.java)
                startActivity(intent)
            }
            R.id.button_scanqr -> {
                intent = Intent(applicationContext, qrcode::class.java)
                startActivity(intent)
            }
        }
    }
}