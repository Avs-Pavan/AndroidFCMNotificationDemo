package com.kevin.androidfcmnotificationdemo.ui

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.kevin.androidfcmnotificationdemo.FCMViewModel


@Composable
fun HomeScreen() {

    val viewModel: FCMViewModel = hiltViewModel()

    val message = viewModel.message.collectAsState()

    val context = LocalContext.current

    Toast.makeText(context, message.value, Toast.LENGTH_SHORT).show()

    Log.d("TAG", "HomeScreen: $message")

    Column(modifier = Modifier.padding(16.dp)) {

        Text(text = "ForeGround : Handled by app.\nBack ground: Handled by system tray.")
        Button(
            onClick = { viewModel.sendSimpleNotification() },
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
        ) {
            Text(text = "Send simple notification")
        }
        Text(text = "Always Handled by app.")
        Button(
            onClick = { viewModel.sendOnlyDataNotification() },
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
        ) {
            Text(text = "Send notification with data payload")
        }

        Text(text = "Fore Ground:\nData payload is handled by app. Notification payload handled by app.")
        Text(text = "Back Ground:\nData payload not Not handled. Notification payload handled by system tray.")
        Button(
            onClick = { viewModel.sendCompositeNotification() },
            modifier = Modifier
                .padding(10.dp)
                .fillMaxWidth()
        ) {
            Text(text = "Composite notification\nWith both data and notification payload")
        }

        Text(text = "Subscribe to topic fcm_topic.")
        Button(
            onClick = { viewModel.subscribeToTopic() },
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
        ) {
            Text(text = "Subscribe to topic")
        }

        Text(text = "Send notification to topic fcm_topic.")
        Button(
            onClick = { viewModel.sendTopicNotification() },
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
        ) {
            Text(text = "Send notification to topic")
        }

        Text(text = "Un-Subscribe from topic fcm_topic.")
        Button(
            onClick = { viewModel.unSubscribeToTopic() },
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
        ) {
            Text(text = "Un-Subscribe from topic")
        }

    }
}