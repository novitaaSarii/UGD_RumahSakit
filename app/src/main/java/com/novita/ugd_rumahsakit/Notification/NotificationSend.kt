package com.novita.ugd_rumahsakit.Notification

import android.app.Notification
import android.app.PendingIntent
import android.content.Intent
import android.graphics.BitmapFactory
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.novita.ugd_rumahsakit.CreateNotification.Companion.CHANNEL_1_ID
import com.novita.ugd_rumahsakit.CreateNotification.Companion.CHANNEL_2_ID
import com.novita.ugd_rumahsakit.R

class NotificationSend : AppCompatActivity() {
    private var notificationManager: NotificationManagerCompat? = null
    private var editTextTitle: EditText? = null
    private var editTextMessage: EditText? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notification_send)
        notificationManager = NotificationManagerCompat.from(this)
        editTextTitle = findViewById(R.id.edit_text_title)
        editTextMessage = findViewById(R.id.edit_text_message)
    }

    fun sendOnChannel1(v: View?) {
        val title = editTextTitle!!.text.toString()
        val message = editTextMessage!!.text.toString()
        val activityIntent = Intent(this, NotificationSend::class.java)
        val contentIntent = PendingIntent.getActivity(
            this,
            0, activityIntent, 0
        )
        val broadcastIntent = Intent(this, NotificationReceiver::class.java)
        broadcastIntent.putExtra("toastMessage", message)
        val actionIntent = PendingIntent.getBroadcast(
            this,
            0, broadcastIntent, PendingIntent.FLAG_UPDATE_CURRENT
        )
        val largeIcon = BitmapFactory.decodeResource(resources, R.drawable.dokter)
        val notification: Notification = NotificationCompat.Builder(this, CHANNEL_1_ID)
            .setSmallIcon(R.drawable.ic_baseline_notifications_24)
            .setContentTitle(title)
            .setContentText(message)
            .setLargeIcon(largeIcon)
            .setStyle(
                NotificationCompat.BigTextStyle()
//                    .bigText(getString(R.string.long_dummy_text))
                    .setBigContentTitle("Big Content Title")
                    .setSummaryText("Summary Text")
            )
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setCategory(NotificationCompat.CATEGORY_MESSAGE)
            .setColor(Color.BLUE)
            .setContentIntent(contentIntent)
            .setAutoCancel(true)
            .setOnlyAlertOnce(true)
            .addAction(R.mipmap.ic_launcher, "Toast", actionIntent)
            .build()
        notificationManager!!.notify(1, notification)
    }

    fun sendOnChannel2(v: View?) {
        val title = editTextTitle!!.text.toString()
        val message = editTextMessage!!.text.toString()
        val notification: Notification = NotificationCompat.Builder(this, CHANNEL_2_ID)
            .setSmallIcon(R.drawable.ic_baseline_notifications_24)
            .setContentTitle(title)
            .setContentText(message)
            .setStyle(
                NotificationCompat.InboxStyle()
                    .addLine("Jangan Lupa Makan")
                    .addLine("Jangan Lupa jaga kesehatan")
                    .addLine("Karena menjaga kesehatan itu penting")
                    .addLine("Jika anda sakit segera hubungi dokter di Rumah Sakit Atma jaya")
                    .setBigContentTitle("Big Content Title")
                    .setSummaryText("Summary Text")
            )
            .setPriority(NotificationCompat.PRIORITY_LOW)
            .build()
        notificationManager!!.notify(2, notification)
    }
}