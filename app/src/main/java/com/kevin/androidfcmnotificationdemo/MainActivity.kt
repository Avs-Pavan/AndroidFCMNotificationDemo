package com.kevin.androidfcmnotificationdemo

import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.google.firebase.messaging.FirebaseMessaging
import com.kevin.androidfcmnotificationdemo.di.Constants
import com.kevin.androidfcmnotificationdemo.ui.HomeScreen
import com.kevin.androidfcmnotificationdemo.ui.theme.AndroidFCMNotificationDemoTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        setContent {
            AndroidFCMNotificationDemoTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    HomeScreen()
                }
            }
        }
    }

    override fun onResume() {
        super.onResume()
        printFcmToken()
    }

    private fun printFcmToken() {
        FirebaseMessaging.getInstance().token.addOnSuccessListener {
            Log.e("Token", it)
            sharedPreferences.edit().putString(Constants.FCM_TOKEN_KEY, it).apply()
        }.addOnCanceledListener {
            Log.e("Token", "cancelled")
        }.addOnFailureListener {
            Log.e("Token", it.message.toString())
            it.printStackTrace()
        }.addOnCompleteListener {
            Log.e("Token", "Completed")
        }
    }

}

