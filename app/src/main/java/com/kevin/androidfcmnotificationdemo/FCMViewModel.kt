package com.kevin.androidfcmnotificationdemo

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.messaging.FirebaseMessaging
import com.kevin.androidfcmnotificationdemo.di.Constants
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FCMViewModel @Inject constructor(private val repository: FcmRepository) : ViewModel() {

    private val _message = MutableStateFlow("")
    val message: StateFlow<String> = _message

    fun sendSimpleNotification() {
        viewModelScope.launch {
            repository.sendSimpleNotification().collectLatest {
                _message.value = it
            }
        }
    }

    fun sendOnlyDataNotification() {
        viewModelScope.launch {
            repository.sendDataNotification().collectLatest {
                _message.value = it
            }
        }
    }

    fun sendCompositeNotification() {
        viewModelScope.launch {
            repository.sendCompositeNotification().collectLatest {
                _message.value = it
            }
        }
    }

    fun subscribeToTopic() {
        FirebaseMessaging.getInstance().subscribeToTopic(Constants.TOPIC)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    // Subscription successful
                    _message.value = ("Subscribed to topic: ${Constants.TOPIC}")
                } else {
                    // Subscription failed
                    _message.value = ("Failed to subscribe to topic: ${Constants.TOPIC}")
                }
            }.addOnFailureListener {
                _message.value =
                    ("Failed to Un-subscribe to topic: ${Constants.TOPIC} ${it.message.toString()}")
            }
    }

    fun unSubscribeToTopic() {
        FirebaseMessaging.getInstance().unsubscribeFromTopic(Constants.TOPIC)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    // Subscription successful
                    _message.value = ("Un-Subscribed to topic: ${Constants.TOPIC}")
                } else {
                    // Subscription failed
                    _message.value = ("Failed to Un-subscribe to topic: ${Constants.TOPIC}")
                }
            }.addOnFailureListener {
                _message.value =
                    ("Failed to Un-subscribe to topic: ${Constants.TOPIC} ${it.message.toString()}")
            }
    }


    fun sendTopicNotification() {
        viewModelScope.launch {
            repository.sendTopicNotification().collectLatest {
                _message.value = it
            }
        }
    }



}