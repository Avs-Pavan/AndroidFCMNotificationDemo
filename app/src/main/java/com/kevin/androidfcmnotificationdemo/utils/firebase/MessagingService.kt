package com.kevin.androidfcmnotificationdemo.utils.firebase

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.media.RingtoneManager
import android.util.Log
import androidx.core.app.NotificationCompat
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import com.kevin.androidfcmnotificationdemo.MainActivity
import com.kevin.androidfcmnotificationdemo.R
import com.kevin.androidfcmnotificationdemo.di.Constants

class MessagingService : FirebaseMessagingService() {

    private val sharedPreferencesEditor by lazy {
        getSharedPreferences(
            Constants.SHARED_PREF_NAME,
            MODE_PRIVATE
        ).edit()
    }

    init {
        Log.e(TAG, "Initialized")
    }

    override fun onNewToken(token: String) {
        super.onNewToken(token)
        Log.e(TAG, "Token: $token")

        // Save token to shared preferences
        sharedPreferencesEditor.putString(Constants.FCM_TOKEN_KEY, token).apply()
    }

    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        super.onMessageReceived(remoteMessage)
        Log.e(TAG, "From: ${remoteMessage.from}")

        // Check if message contains a data payload.
        if (remoteMessage.data.isNotEmpty()) {
            Log.e(TAG, "Message data payload: ${remoteMessage.data}")
            remoteMessage.data.let {
                sendNotification(it["title"].toString(), it["body"].toString())
            }
        }

        // Check if message contains a notification payload.
        remoteMessage.notification?.let {
            Log.e(TAG, "Message Notification Body: ${it.body}")
            sendNotification(it.title.toString(), it.body.toString())
        }
    }

    companion object {
        const val TAG = "MessagingService"
    }


    private fun sendNotification(title: String, message: String) {
        val defaultSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)

        val intent = Intent(this, MainActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP

        val pendingIntent = PendingIntent.getActivity(
            applicationContext, 0,
            intent,
            PendingIntent.FLAG_IMMUTABLE
        )

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            val notificationChannel = NotificationChannel(
                "1",
                getString(R.string.app_name),
                NotificationManager.IMPORTANCE_HIGH
            )
            notificationChannel.description = "Fcm notification demo"
            notificationChannel.enableLights(true)
            notificationChannel.enableVibration(true)
            val notificationManager = getSystemService(NotificationManager::class.java)
            notificationManager.createNotificationChannel(notificationChannel)
        }

        val notification = NotificationCompat.Builder(applicationContext, "1")
            .setSmallIcon(R.mipmap.ic_launcher)
            .setContentTitle("App handled: $title")
            .setContentText(message)
            .setContentIntent(pendingIntent)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setSound(defaultSound)

        val notificationManager: NotificationManager =
            getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.notify(System.currentTimeMillis().toInt(), notification.build())
    }


}