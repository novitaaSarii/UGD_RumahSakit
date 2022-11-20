package com.novita.ugd_rumahsakit.profile

import android.content.Intent
import android.os.Bundle
import android.provider.ContactsContract
import android.view.View
import android.view.WindowManager
import android.widget.LinearLayout
import android.widget.SearchView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
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
import com.novita.ugd_rumahsakit.adapters.ProfileAdapter
import com.novita.ugd_rumahsakit.api.ProfileApi
import com.novita.ugd_rumahsakit.models.profile
import org.json.JSONObject
import java.lang.Exception
import java.nio.charset.StandardCharsets

class ProfileActivity : AppCompatActivity() {

    private var srProfile: SwipeRefreshLayout? = null
    private var adapter: ProfileAdapter? = null
    private var svProfile: SearchView? = null
    private var layoutLoading: LinearLayout? = null
    private var queue: RequestQueue? = null

    companion object {
        const val LAUNCH_ADD_ACTIVITY = 123
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        queue = Volley.newRequestQueue(this)
        layoutLoading = findViewById(R.id.layout_loading)
        srProfile =  findViewById(R.id.sr_profile)
        svProfile = findViewById(R.id.sv_profile)

        srProfile?.setOnRefreshListener( { allProfile() })
        svProfile?.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextChange(p0: String?): Boolean {
                return false
            }

            override fun onQueryTextSubmit(s: String?): Boolean {
                adapter!!.filter.filter(s)
                return false
            }
        })

        val fabAdd = findViewById<FloatingActionButton>(R.id.fab_add)
        fabAdd.setOnClickListener {
            val  i = Intent( this@ProfileActivity, AddEditActivityProfile::class.java)
            startActivityForResult(i, LAUNCH_ADD_ACTIVITY)
        }

        val rvProduk = findViewById<RecyclerView>(R.id.rv_profile)
        adapter = ProfileAdapter(ArrayList(), this)
        rvProduk.layoutManager = LinearLayoutManager(this)
        rvProduk.adapter = adapter
        allProfile()
    }

    private fun allProfile() {
        srProfile!!.isRefreshing = true
        val stringRequest: StringRequest = object :
            StringRequest(Method.GET, ProfileApi.GET_ALL_URL, Response.Listener { response ->
                val gson = Gson()
                var profile: Array<profile> =
                    gson.fromJson(response, Array<profile>::class.java)

                adapter!!.setprofileList(profile)
                adapter!!.filter.filter(svProfile!!.query)
                srProfile!!.isRefreshing = false

                if (profile.isEmpty())
                    Toast.makeText(
                        this@ProfileActivity,
                        "Data Berhasil Diambil!!",
                        Toast.LENGTH_SHORT
                    )
                        .show()
                else
                    Toast.makeText(this@ProfileActivity, "Data Kosong", Toast.LENGTH_SHORT)
                        .show()
            }, Response.ErrorListener { error ->
                srProfile!!.isRefreshing = false
                try {
                    val responseBody =
                        String(error.networkResponse.data, StandardCharsets.UTF_8)
                    val errors = JSONObject(responseBody)
                    Toast.makeText(
                        this@ProfileActivity,
                        errors.getString("message"),
                        Toast.LENGTH_SHORT
                    ).show()
                } catch (e: Exception) {
                    Toast.makeText(this@ProfileActivity, e.message, Toast.LENGTH_SHORT).show()
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

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == LAUNCH_ADD_ACTIVITY && resultCode == RESULT_OK) allProfile()
    }

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