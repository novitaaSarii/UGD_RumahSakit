package com.novita.ugd_rumahsakit.spesialisnamaDokter

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.WindowManager
import android.widget.*
import com.android.volley.AuthFailureError
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import com.novita.ugd_rumahsakit.R
import com.novita.ugd_rumahsakit.api.DokterApi
import com.novita.ugd_rumahsakit.models.dokter
import org.json.JSONObject
import java.nio.charset.StandardCharsets

class Add_edit_Activity : AppCompatActivity() {

    private var etiddokter: EditText? = null
    private var etnama: EditText? = null
    private var edspesialis: EditText? = null
    private var edalamat: EditText? = null
    private var layoutLoading: LinearLayout? = null
    private var queue: RequestQueue? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_edit_activitu)

        // Pendeklarasian request queue
        queue = Volley.newRequestQueue(this)
        etiddokter = findViewById(R.id.et_iddokter)
        etnama = findViewById(R.id.et_nama)
        edspesialis = findViewById(R.id.ed_spesialis)
        edalamat = findViewById(R.id.ed_alamat)
        layoutLoading = findViewById(R.id.layout_loading)

        val btnCancel = findViewById<Button>(R.id.btn_cancel)
        btnCancel.setOnClickListener { finish() }
        val btnSave = findViewById<Button>(R.id.btn_save)
        val tvTitle = findViewById<TextView>(R.id.tv_title)
        val id = intent.getLongExtra("id", -1)
        if(id == -1L) {
            tvTitle.setText("Tambah Dokter")
            btnSave.setOnClickListener { createDokter() }
        } else {
            tvTitle.setText("Edit Dokter")
            getDokterById(id)
            btnSave.setOnClickListener { updateDokter(id) }
        }
    }

    private fun getDokterById(id: Long) {
        //Fungsi untuk menampilkan data dokter berdasarkan id
        setLoading(true)
        val stringRequest: StringRequest = object :
            StringRequest(Method.GET, DokterApi.GET_BY_ID_URL + id, Response.Listener { response ->
                val gson = Gson()
                val dokter = gson.fromJson(response, dokter::class.java)

                etiddokter!!.setText(dokter.dokterid)
                etnama!!.setText(dokter.dokternama)
                edspesialis!!.setText(dokter.dokterspesialis)
                edalamat!!.setText(dokter.dokteralamat)

                Toast.makeText(this@Add_edit_Activity, "Data berhasil diambil!", Toast.LENGTH_SHORT).show()
                setLoading(false)
            }, Response.ErrorListener { error ->
                setLoading(false)
                try{
                    val responseBody = String(error.networkResponse.data, StandardCharsets.UTF_8)
                    val errors = JSONObject(responseBody)
                    Toast.makeText(
                        this@Add_edit_Activity,
                        errors.getString("message"),
                        Toast.LENGTH_SHORT
                    ).show()
                } catch (e: Exception) {
                    Toast.makeText(this@Add_edit_Activity, e.message, Toast.LENGTH_SHORT).show()
                }
            }) {
            @Throws(AuthFailureError::class)
            override fun getHeaders(): Map<String, String> {
                val headers = HashMap<String, String>()
                headers["Accept"] = "application/json"
                return headers
            }
        }
        queue!!.add(stringRequest)
    }
    private fun createDokter() {
        // Fungsi untuk menambah data dokter.
        setLoading(true)

        val dokter = dokter(
            etiddokter!!.text.toString(),
            etnama!!.text.toString(),
            edspesialis!!.text.toString(),
            edalamat!!.text.toString()
        )
        Log.d("rrrrrrrrrr",dokter.dokterid)
        val stringRequest: StringRequest =
            object : StringRequest(Method.POST, DokterApi.ADD_URL, Response.Listener { response ->
                val gson = Gson()
                var dokter = gson.fromJson(response, dokter::class.java)

                if(dokter != null)
                    Toast.makeText(this@Add_edit_Activity, "Data Berhasil Ditambah", Toast.LENGTH_SHORT).show()

                val returnIntent = Intent()
                setResult(RESULT_OK, returnIntent)
                finish()

                setLoading(false)
            }, Response.ErrorListener { error ->
                setLoading(false)
                try{
                    val responseBody = String(error.networkResponse.data, StandardCharsets.UTF_8)
                    val errors = JSONObject(responseBody)
                    Toast.makeText(
                        this@Add_edit_Activity,
                        errors.getString("message"),
                        Toast.LENGTH_SHORT
                    ).show()
                } catch (e: Exception) {
                    Toast.makeText(this@Add_edit_Activity, e.message, Toast.LENGTH_SHORT).show()
                }
            }) {
                @Throws(AuthFailureError::class)
                override fun getHeaders(): Map<String, String> {
                    val headers = HashMap<String, String>()
                    headers["Accept"] = "application/json"
                    return headers
                }

                @Throws(AuthFailureError::class)
                override fun getBody(): ByteArray {
                    val gson = Gson()
                    val requestBody = gson.toJson(dokter)
                    Log.d("rrrrrrrrrr",requestBody.toString())
                    return requestBody.toByteArray(StandardCharsets.UTF_8)
                }

                override fun getBodyContentType(): String {
                    return "application/json"
                }
            }

        // Menambahkan request ke request queue
        queue!!.add(stringRequest)
    }

    private fun updateDokter(id: Long) {
        // Fungsi untuk engubah data mahasiswa.
        setLoading(true)

        val dokter = dokter(
            etiddokter!!.text.toString(),
            etnama!!.text.toString(),
            edspesialis!!.text.toString(),
            edalamat!!.text.toString()
        )

        val stringRequest: StringRequest = object :
            StringRequest(Method.PUT, DokterApi.UPDATE_URL + id, Response.Listener { response ->
                val gson = Gson()
                var dokter = gson.fromJson(response, dokter::class.java)

                if (dokter != null)
                    Toast.makeText(this@Add_edit_Activity, "Data berhasil diupdate", Toast.LENGTH_SHORT).show()

                val returnIntent = Intent()
                setResult(RESULT_OK, returnIntent)
                finish()

                setLoading(false)
            }, Response.ErrorListener { error ->
                setLoading(false)
                try{
                    val responseBody = String(error.networkResponse.data, StandardCharsets.UTF_8)
                    val errors = JSONObject(responseBody)
                    Toast.makeText(
                        this@Add_edit_Activity,
                        errors.getString("message"),
                        Toast.LENGTH_SHORT
                    ).show()
                } catch (e:Exception) {
                    Toast.makeText(this@Add_edit_Activity, e.message, Toast.LENGTH_SHORT).show()
                }
            }) {
            @Throws(AuthFailureError::class)
            override fun getHeaders(): Map<String, String> {
                val headers = HashMap<String, String>()
                headers["Accept"] = "application/json"
                return headers
            }

            @Throws(AuthFailureError::class)
            override fun getBody(): ByteArray {
                val gson = Gson()
                val requestBody = gson.toJson(dokter)
                return requestBody.toByteArray(StandardCharsets.UTF_8)
            }

            override fun getBodyContentType(): String {
                return "application/json"
            }
        }
        queue!!.add(stringRequest)
    }

    //Fungsi ini digunakan menampilkan layout loading
    private fun setLoading(isLoading: Boolean) {
        if(isLoading) {
            window.setFlags(
                WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE
            )
            layoutLoading!!.visibility = View.VISIBLE
        } else {
            window.clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE)
            layoutLoading!!.visibility = View.INVISIBLE
        }
    }
}