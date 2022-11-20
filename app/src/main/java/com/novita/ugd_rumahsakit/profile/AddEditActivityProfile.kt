package com.novita.ugd_rumahsakit.profile

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.WindowManager
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.AuthFailureError
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import com.novita.ugd_rumahsakit.R
import com.novita.ugd_rumahsakit.api.ProfileApi
import com.novita.ugd_rumahsakit.models.profile
import kotlinx.android.synthetic.main.activity_edit.*
import org.json.JSONObject
import java.nio.charset.StandardCharsets
import java.util.HashMap

class AddEditActivityProfile : AppCompatActivity() {

    private var etusername: EditText? = null
    private var etpassword: EditText? = null
    private var edemail: EditText? = null
    private var edtanggal: EditText? = null
    private var ednomor: EditText? = null
    private var layoutLoading: LinearLayout? = null
    private var queue: RequestQueue? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_edit_activitu)

        // Pendeklarasian request queue
        queue = Volley.newRequestQueue(this)
        etusername = findViewById(R.id.et_username)
        etpassword= findViewById(R.id.et_password)
        edemail = findViewById(R.id.ed_email)
        edtanggal = findViewById(R.id.ed_tanggal)
        ednomor = findViewById(R.id.ed_nomor)
        layoutLoading = findViewById(R.id.layout_loading)

        val btnCancel = findViewById<Button>(R.id.btn_cancel)
        btnCancel.setOnClickListener { finish() }
        val btnSave = findViewById<Button>(R.id.btn_save)
        val tvTitle = findViewById<TextView>(R.id.tv_title)
        val id = intent.getLongExtra("id", -1)
        if(id == -1L) {
            tvTitle.setText("Tambah User")
            btnSave.setOnClickListener { createProfile() }
        } else {
            tvTitle.setText("Edit User")
            getProfileByUsername(username)
            btnSave.setOnClickListener { updateProfile(username) }
        }
    }

    private fun getProfileByUsername(username: TextView) {

        setLoading(true)
        val stringRequest: StringRequest = object :
            StringRequest(Method.GET, ProfileApi.GET_BY_USERNAME_URL + username, Response.Listener { response ->
                val gson = Gson()
                val profile = gson.fromJson(response, profile::class.java)

                etusername!!.setText(profile.username)
                etpassword!!.setText(profile.password)
                edemail!!.setText(profile.email)
                edtanggal!!.setText(profile.tanggal)
                ednomor!!.setText(profile.nomor)

                Toast.makeText(this@AddEditActivityProfile, "Data berhasil diambil!", Toast.LENGTH_SHORT).show()
                setLoading(false)
            }, Response.ErrorListener { error ->
                setLoading(false)
                try{
                    val responseBody = String(error.networkResponse.data, StandardCharsets.UTF_8)
                    val errors = JSONObject(responseBody)
                    Toast.makeText(
                        this@AddEditActivityProfile,
                        errors.getString("message"),
                        Toast.LENGTH_SHORT
                    ).show()
                } catch (e: Exception) {
                    Toast.makeText(this@AddEditActivityProfile, e.message, Toast.LENGTH_SHORT).show()
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
    private fun createProfile() {
        // Fungsi untuk menambah data dokter.
        setLoading(true)

        val profile = profile(
            etusername!!.text.toString(),
            etpassword!!.text.toString(),
            edemail!!.text.toString(),
            edtanggal!!.text.toString(),
            ednomor!!.text.toString()
        )
        Log.d("rrrrrrrrrr",profile.username)
        val stringRequest: StringRequest =
            object : StringRequest(Method.POST, ProfileApi.ADD_URL, Response.Listener { response ->
                val gson = Gson()
                var profile = gson.fromJson(response, profile::class.java)

                if(profile != null)
                    Toast.makeText(this@AddEditActivityProfile, "Data Berhasil Ditambah", Toast.LENGTH_SHORT).show()

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
                        this@AddEditActivityProfile,
                        errors.getString("message"),
                        Toast.LENGTH_SHORT
                    ).show()
                } catch (e: Exception) {
                    Toast.makeText(this@AddEditActivityProfile, e.message, Toast.LENGTH_SHORT).show()
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
                    val requestBody = gson.toJson(profile)
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

    private fun updateProfile(username: TextView) {
        // Fungsi untuk engubah data mahasiswa.
        setLoading(true)

        val profile = profile(
            etusername!!.text.toString(),
            etpassword!!.text.toString(),
            edemail!!.text.toString(),
            edtanggal!!.text.toString(),
            ednomor!!.text.toString()
        )

        val stringRequest: StringRequest = object :
            StringRequest(Method.PUT, ProfileApi.UPDATE_URL + username, Response.Listener { response ->
                val gson = Gson()
                var profile = gson.fromJson(response, profile::class.java)

                if (profile != null)
                    Toast.makeText(this@AddEditActivityProfile, "Data berhasil diupdate", Toast.LENGTH_SHORT).show()

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
                        this@AddEditActivityProfile,
                        errors.getString("message"),
                        Toast.LENGTH_SHORT
                    ).show()
                } catch (e:Exception) {
                    Toast.makeText(this@AddEditActivityProfile, e.message, Toast.LENGTH_SHORT).show()
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
                val requestBody = gson.toJson(profile)
                return requestBody.toByteArray(StandardCharsets.UTF_8)
            }

            override fun getBodyContentType(): String {
                return "application/json"
            }
        }
        queue!!.add(stringRequest)
    }


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