package com.novita.ugd_rumahsakit

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.android.volley.AuthFailureError
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import com.novita.ugd_rumahsakit.MVVM.registerAdapter
import com.novita.ugd_rumahsakit.MainAdapter.CreateAccountAdapter
import com.novita.ugd_rumahsakit.Notification.NotificationReceiver
import com.novita.ugd_rumahsakit.Task.TaskList
import com.novita.ugd_rumahsakit.api.userApi
import com.novita.ugd_rumahsakit.databinding.ActivityCreateraccountBinding
import com.novita.ugd_rumahsakit.models.responseUser
import com.novita.ugd_rumahsakit.models.user
import com.novita.ugd_rumahsakit.room.register
import com.novita.ugd_rumahsakit.room.registerDB
import com.orhanobut.logger.AndroidLogAdapter
import com.orhanobut.logger.Logger
import kotlinx.android.synthetic.main.activity_createraccount.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.json.JSONObject
import java.nio.charset.StandardCharsets

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

    private var queue: RequestQueue? = null


    private val CHANNEL_ID_1 ="channel_notifikasi_01"
    private val notificationId1 = 101
    private val notificationId2 = 102
    private val notificationId3 = 103
    val GROUP_KEY_WORK_EMAIL = "com.android.example.WORK_EMAIL"
    private var Email = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+"
    private var nomor= "^[+]?[0-9]{10,13}$"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_createraccount)
        queue = Volley.newRequestQueue(this)
        /*
            View Binding
        */
        binding = ActivityCreateraccountBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        val adapter = CreateAccountAdapter(TaskList.tasklist)

        //untuk mengimplementasikan blogger ke logcat biar lebih rapi di error
        Logger.addLogAdapter(AndroidLogAdapter())

        createNotificationChannel()


        binding?.btnCreate?.setOnClickListener {
            var checkCreateAccount = false
//            Logger.d("Selamat datang Rumah Sakit Atma Jaya");
            val username : String =  binding?.etUsername?.editText?.text.toString()
            val password : String =  binding?.etPassword?.editText?.text.toString()
            val email  : String =  binding?.etEmail?.editText?.text.toString()
            val tanggalLahir : String =  binding?.etTanggalLahir?.editText?.text.toString()
            val nomorTelepon : String =  binding?.etNomorTelepon?.editText?.text.toString()

            var register = true

            if(username.isEmpty()){
                etUsername.setError("username Tidak boleh kosong")
                return@setOnClickListener
            }

            if(password.isEmpty()) {
                etPassword.setError("password Tidak boleh kosong")
                return@setOnClickListener
            }
            if(email.isEmpty()){
                etEmail.setError("email Tidak boleh kosong")
                return@setOnClickListener
            }

            else if(binding?.etEmail?.editText?.text.toString().matches(Email.toRegex())){
                etEmail.setError("Format Email Harus sesuai")
                return@setOnClickListener
            }
            if(tanggalLahir.isEmpty()){
                etTanggalLahir.setError("Tanggal Lahir Tidak boleh kosong")
                return@setOnClickListener
            }
            if(nomorTelepon.isEmpty()){
                etNomorTelepon.setError("Nomor Tidak boleh kosong")
                return@setOnClickListener
            }
            else if(binding?.etNomorTelepon?.editText?.text.toString().matches(nomor.toRegex())){
                etNomorTelepon.setError("Harus 10-13 angka")
                return@setOnClickListener
            }


//            if(!register){
                createregister()
//            }

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
            /*
               Notifikasi
            */

            sendNotifiaction1()
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

    private fun createNotificationChannel(){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.ECLAIR_0_1){
            val name="Berhasil Register"
            val descriptionText ="Berhasil Register1"

            val channel1= NotificationChannel(CHANNEL_ID_1, name, NotificationManager.IMPORTANCE_DEFAULT).apply {
                description= descriptionText
            }

            val notificationManager: NotificationManager =
                getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel1)
        }
    }

    private fun sendNotifiaction1(){
        val intent : Intent = Intent(this, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }

        val pendingIntent: PendingIntent = PendingIntent.getActivity(this, 0, intent, 0)

        val picture = BitmapFactory.decodeResource(resources, R.drawable.dokter)

        val broadcastIntent: Intent= Intent(this, NotificationReceiver::class.java)
        broadcastIntent.putExtra("toasMessage", binding?.etUsername?.editText?.text.toString())
        val actionIntent = PendingIntent.getBroadcast(this, 0, broadcastIntent, PendingIntent.FLAG_UPDATE_CURRENT)

        val builder = NotificationCompat.Builder(this, CHANNEL_ID_1)
            .setSmallIcon(R.drawable.ic_baseline_looks_one_24)
            .setContentText("Berhasil Register")
            .setCategory(NotificationCompat.CATEGORY_MESSAGE)
            .setColor(Color.BLUE)
            .setAutoCancel(true)
            .setOnlyAlertOnce(true)
            .setContentIntent(pendingIntent)
            .setLargeIcon(picture)
            .setStyle(NotificationCompat.BigPictureStyle()
                .bigLargeIcon(null)
                .bigPicture(picture))
            .addAction(R.mipmap.ic_launcher, "Toast", actionIntent)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)

        val builder2 = NotificationCompat.Builder(this, CHANNEL_ID_1)
            .setSmallIcon((R.drawable.ic_baseline_looks_two_24))
            .setContentText("Hallo Kami mempunyai Penawaran menarik")
            .setColor(Color.BLUE)
            .setAutoCancel(true)
            .setOnlyAlertOnce(true)
            .setContentIntent(pendingIntent)
            .addAction(R.mipmap.ic_launcher, "Toast", actionIntent)
            .setPriority(NotificationCompat.PRIORITY_LOW)

        val summaryNotification = NotificationCompat.Builder(this, CHANNEL_ID_1)
            .setSmallIcon((R.drawable.ic_baseline_looks_one_24))
            .setAutoCancel(true)
            .setOnlyAlertOnce(true)
            .setPriority(NotificationCompat.PRIORITY_LOW)
            .setGroup(GROUP_KEY_WORK_EMAIL)
            .setGroupSummary(true)
            .build()

        with(NotificationManagerCompat.from(this)){
            notify(notificationId1, builder.build())
            notify(notificationId2, builder2.build())
            notify(notificationId3, summaryNotification)
        }

    }

    private fun createregister(){
        val register = user(
            0,
            binding?.etUsername?.editText?.text.toString(),
            binding?.etPassword?.editText?.text.toString(),
            binding?.etEmail?.editText?.text.toString(),
            binding?.etTanggalLahir?.editText?.text.toString(),
            binding?.etNomorTelepon?.editText?.text.toString(),
        )


        val stringRequest: StringRequest =
            object : StringRequest(Method.POST, userApi.REGISTER_URL, Response.Listener { response ->
                val gson = Gson()
                val register = gson.fromJson(response, responseUser::class.java)

                if(register != null)
                    Toast.makeText(this@MainActivity, "Berhasil Register", Toast.LENGTH_SHORT).show()

                sendNotifiaction1()

                val returnIntent = Intent()
                setResult(RESULT_OK, returnIntent)
                finish()
            }, Response.ErrorListener { error ->
                Log.d("register",error.toString())
                try{
                    val responseBody = String(error.networkResponse.data, StandardCharsets.UTF_8)
                    val errors = JSONObject(responseBody)
                    Toast.makeText(this@MainActivity,
                        errors.getString("message"),
                        Toast.LENGTH_SHORT
                    ).show()
                }
                catch (e:Exception){
                    Toast.makeText(this@MainActivity, e.message, Toast.LENGTH_SHORT).show()
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
                    val requestBody = gson.toJson(register)
                    Log.d("rrrrrrrrrr",requestBody.toString())
                    return requestBody.toByteArray(StandardCharsets.UTF_8)
                }

                override fun getBodyContentType(): String {
                    return "application/json"
                }
            }
        queue!!.add(stringRequest)
    }
}



