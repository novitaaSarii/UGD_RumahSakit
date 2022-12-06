package com.novita.ugd_rumahsakit

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.get
import com.android.volley.AuthFailureError
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.google.gson.Gson
import com.master.permissionhelper.PermissionHelper
import com.novita.ugd_rumahsakit.MVVM.registerAdapter
import com.novita.ugd_rumahsakit.api.userApi
import com.novita.ugd_rumahsakit.models.responseUser
import com.novita.ugd_rumahsakit.models.user
import com.novita.ugd_rumahsakit.room.registerDB
import com.shashank.sony.fancytoastlib.FancyToast
import org.json.JSONObject
import java.nio.charset.StandardCharsets

class Tampilan : AppCompatActivity() {
    val db by lazy{ registerDB(this) }
    lateinit var registerAdapter: registerAdapter

    private lateinit var inputUsername: TextInputLayout
    private lateinit var inputpassword: TextInputLayout
    private lateinit var tampilanlayout: ConstraintLayout
    private var queue: RequestQueue? = null

    lateinit var Register: Bundle
    lateinit var vUsername: String
    lateinit var vPassword: String

    var sharePreferences: SharedPreferences? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tampilan)

        //untuk permisi buka izin
        val permissionHelper = PermissionHelper(this, arrayOf(Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE), 100)
        //Request all permission
        permissionHelper?.requestAll {
            Log.d("Permision", "All permission granted")
        }

        setTitle("UGD2_RumahSakit")

        inputUsername= findViewById(R.id.inputLayoutUsername)
        inputpassword=findViewById(R.id.inputLayoutPassword)
        tampilanlayout=findViewById(R.id.tampilanLayout)
        val btnRegister : Button = findViewById(R.id.btnRegister)
        val btnLogin : Button = findViewById(R.id.btnLogin)

        sharePreferences = getSharedPreferences("myPerference", Context.MODE_PRIVATE)

        btnLogin.setOnClickListener(View.OnClickListener{
            queue = Volley.newRequestQueue(this)
            val username: String = inputUsername.getEditText()?.getText().toString()
            val password: String = inputpassword.getEditText()?.getText().toString()
            var checkLogin = true

            if(username.isEmpty()){
                inputUsername.setError("Username wrong")
                checkLogin = false
                return@OnClickListener
            }

            if(password.isEmpty()){
                inputpassword.setError("Password Wrong")
                checkLogin=false
                return@OnClickListener
            }

            if(!checkLogin){
                return@OnClickListener
            }else{
                try{
                    login()
                } catch(e : Error){
                    inputUsername.setError(e.message)
                    inputpassword.setError(e.message)
                    return@OnClickListener
                }
            }

            val extra : Bundle? = getIntent().getBundleExtra("register")
            if (extra == null){
                FancyToast.makeText(this,"Data Tidak ada atau Data Salah!",FancyToast.LENGTH_LONG,FancyToast.ERROR,true).show()
                inputUsername.setError("Username Salah")
                inputpassword.setError("Password Salah ")
                return@OnClickListener
            }
            else{
                Log.d("bundle",extra.toString())
                vUsername = extra.getString("username")!!
                vPassword = extra.getString("password")!!

            }

            if(username == vUsername  && password == vPassword) checkLogin = true
            if(!checkLogin) return@OnClickListener

            /*
              share preference untuk membandingkan username waktu login
            */

            val editor: SharedPreferences.Editor = sharePreferences!!.edit()
            editor.putString("username", username)
            editor.apply()
            FancyToast.makeText(this,"Selamat Berhasil Login ",FancyToast.LENGTH_LONG,FancyToast.SUCCESS,true).show()
            val moveHome = Intent(this@Tampilan, HomeActivity::class.java)
            startActivity(moveHome)
        })

        btnRegister.setOnClickListener{

            val moveMain = Intent(this@Tampilan, MainActivity::class.java)
            startActivity(moveMain)
        }

    }

    private fun login(){
        val login = user(
            0,
            inputUsername.getEditText()?.getText().toString(),
            inputpassword.getEditText()?.getText().toString(),
            "","",""
        )

        val stringRequest: StringRequest =
            object : StringRequest(Method.POST, userApi.LOGIN_URL, Response.Listener { response ->
                val gson = Gson()
                val register = gson.fromJson(response, responseUser::class.java)

                if(register != null)
                    Toast.makeText(this@Tampilan, "Berhasil Login", Toast.LENGTH_SHORT).show()

                val returnIntent = Intent()
                setResult(RESULT_OK, returnIntent)
                finish()

            }, Response.ErrorListener { error -> Log.d("Login",error.toString())
                try{
                    val responseBody = String(error.networkResponse.data, StandardCharsets.UTF_8)
                    val errors = JSONObject(responseBody)
                    Toast.makeText(this@Tampilan,
                        errors.getString("message"),
                        Toast.LENGTH_SHORT
                    ).show()
                }
                catch (e:Exception){
                    Toast.makeText(this@Tampilan, e.message, Toast.LENGTH_SHORT).show()
                }
            }){
                @Throws(AuthFailureError::class)
                override fun getHeaders(): Map<String, String>{
                    val headers = HashMap<String, String>()
                    headers["Accept"] = "application/json"
                    return headers
                }
                @Throws(AuthFailureError::class)
                override fun getBody(): ByteArray{
                    val gson = Gson()
                    val requestBody = gson.toJson(login)
                    return requestBody.toByteArray(StandardCharsets.UTF_8)
                }

                override fun getBodyContentType(): String {
                    return "application/json"
                }
            }
        queue!!.add(stringRequest)
    }

}

