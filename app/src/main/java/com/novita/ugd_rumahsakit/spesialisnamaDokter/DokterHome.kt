package com.novita.ugd_rumahsakit.spesialisnamaDokter

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.WindowManager
import android.widget.LinearLayout
import android.widget.SearchView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.android.volley.AuthFailureError
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.gson.Gson
import com.novita.ugd_rumahsakit.R
import com.novita.ugd_rumahsakit.adapters.DokterAdapter
import com.novita.ugd_rumahsakit.api.DokterApi
import com.novita.ugd_rumahsakit.models.ResponseDataDokter
import com.novita.ugd_rumahsakit.models.dokter
import org.json.JSONObject
import java.nio.charset.StandardCharsets

class dokterHome : AppCompatActivity() {

    private var srdokter: SwipeRefreshLayout? = null
    private var adapter: DokterAdapter? = null
    private var svdokter: SearchView? = null
    private var layoutLoading: LinearLayout? = null
    private var queue: RequestQueue?= null

    companion object {
        const val LAUNCH_ADD_ACTIVITY = 123
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dokter_home)

        queue = Volley.newRequestQueue(this)
        layoutLoading = findViewById(R.id.layout_loading)
        srdokter = findViewById(R.id.sr_dokter)
        svdokter = findViewById(R.id.sv_dokter)

        srdokter?.setOnRefreshListener(SwipeRefreshLayout.OnRefreshListener { allDokter()  })
        svdokter?.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextChange(s: String?): Boolean {
                return false
            }

            override fun onQueryTextSubmit(s: String?): Boolean {
                adapter!!.filter.filter(s)
                return false
            }
        })

        val fabAdd = findViewById<FloatingActionButton>(R.id.fab_add)
        fabAdd.setOnClickListener {
            val i = Intent(this@dokterHome, Add_edit_Activity::class.java)
            startActivityForResult(i, LAUNCH_ADD_ACTIVITY)
        }

        val rvProduk = findViewById<RecyclerView>(R.id.rv_dokter)
        adapter = DokterAdapter(ArrayList(), this)
        rvProduk.layoutManager = LinearLayoutManager(this)
        rvProduk.adapter = adapter
        allDokter()
    }

    private fun allDokter() {
        srdokter!!.isRefreshing = true
        val stringRequest: StringRequest = object :
            StringRequest(Method.GET, DokterApi.GET_ALL_URL, Response.Listener { response ->
                val gson = Gson()
                Log.d("dokterrrrrrrr",response.toString())
                var res: ResponseDataDokter =
                    gson.fromJson(response, ResponseDataDokter::class.java)
                var dokter = res.data
                adapter!!.setdokterList(dokter.toTypedArray())
                adapter!!.filter.filter(svdokter!!.query)
                srdokter!!.isRefreshing = false

                if (!dokter.isEmpty())
                    Toast.makeText(
                        this@dokterHome,
                        "Data Berhasil Diambil!",
                        Toast.LENGTH_SHORT
                    )
                        .show()
                else
                    Toast.makeText(this@dokterHome, "Data Kosong!", Toast.LENGTH_SHORT)
                        .show()
            }, Response.ErrorListener { error ->
                Log.d("dokterrrrrrrr",error.toString())
                srdokter!!.isRefreshing = false
                try {
                    val responseBody =
                        String(error.networkResponse.data, StandardCharsets.UTF_8)
                    val errors = JSONObject(responseBody)
                    Toast.makeText(
                        this@dokterHome,
                        errors.getString("message"),
                        Toast.LENGTH_SHORT
                    ).show()
                } catch (e: Exception) {
                    Toast.makeText(this@dokterHome, e.message, Toast.LENGTH_SHORT).show()
                }
            }) {
            // Menambahkan header pada request
            @Throws(AuthFailureError::class)
            override fun getHeaders(): Map<String, String> {
                val headers = HashMap<String, String>()
                headers["Accept"] = "application/json"
                return headers
            }
        }
        queue!!.add(stringRequest)
    }

    fun deleteDokter(id: Long) {
        setLoading(true)
        val stringRequest: StringRequest = object :
            StringRequest(Method.DELETE, DokterApi.DELETE_URL + id, Response.Listener { response ->
                setLoading(false)

                val gson = Gson()
                var dokter = gson.fromJson(response, dokter::class.java)
                if(dokter != null)
                    Toast.makeText(this@dokterHome, "Data Berhasil Dihapus!", Toast.LENGTH_SHORT).show()
                allDokter()
            }, Response.ErrorListener { error ->
                setLoading(false)
                try{
                    val responseBody = String(error.networkResponse.data, StandardCharsets.UTF_8)
                    val errors = JSONObject(responseBody)
                    Toast.makeText(
                        this@dokterHome,
                        errors.getString("message"),
                        Toast.LENGTH_SHORT
                    ).show()
                } catch (e: java.lang.Exception) {
                    Toast.makeText(this@dokterHome, e.message, Toast.LENGTH_SHORT).show()
                }
            }) {
            // Menambahkan header pada request
            @Throws(AuthFailureError::class)
            override fun getHeaders(): Map<String, String> {
                val headers = java.util.HashMap<String, String>()
                headers["Accept"] = "application/json"
                return headers
            }
        }
        queue!!.add(stringRequest)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == LAUNCH_ADD_ACTIVITY && resultCode == RESULT_OK) allDokter()
    }

    // Fungsi ini digunakan menampilkan layout loading
    private fun setLoading(isLoading: Boolean) {
        if (isLoading) {
            window.setFlags(
                WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE
            )
            layoutLoading!!.visibility = View.VISIBLE
        } else {
            window.clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE)
            layoutLoading!!.visibility = View.GONE
        }
    }
}