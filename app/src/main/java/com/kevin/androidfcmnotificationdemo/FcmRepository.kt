package com.kevin.androidfcmnotificationdemo

import android.content.SharedPreferences
import com.kevin.androidfcmnotificationdemo.di.Constants
import com.kevin.androidfcmnotificationdemo.di.api.FCMApiService
import com.kevin.androidfcmnotificationdemo.dto.FcmDataPayload
import com.kevin.androidfcmnotificationdemo.dto.FcmNotificationPayload
import com.kevin.androidfcmnotificationdemo.dto.FcmNotificationRequest
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class FcmRepository @Inject constructor(
    private val fcmApiService: FCMApiService,
    private val preferences: SharedPreferences,
) {

    suspend fun sendSimpleNotification() = flow {
        try {
            val response = fcmApiService.sendSimpleNotification(
                FcmNotificationRequest(
                    preferences.getString(Constants.FCM_TOKEN_KEY, "")!!,
                    FcmNotificationPayload("Simple notification", "Simple notification")
                )
            )
            if (response.isSuccessful) {
                emit("Notification sent Successfully")
            } else {
                emit("Send notification failed")
            }
        } catch (e: Exception) {
            emit("Notification Exception: ${e.message}")
        }

    }

    suspend fun sendDataNotification() = flow {
        try {
            val response = fcmApiService.sendSimpleNotification(
                FcmNotificationRequest(
                    preferences.getString(Constants.FCM_TOKEN_KEY, "")!!,
                    data = FcmDataPayload(
                        "Data payload",
                        "Notification with data body",
                        "Data message"
                    )
                )
            )
            if (response.isSuccessful) {
                emit("Notification with data payload sent Successfully")
            } else {
                emit("Send notification failed")
            }
        } catch (e: Exception) {
            emit("Notification Exception: ${e.message}")
        }

    }

    suspend fun sendCompositeNotification() = flow {
        try {
            val response = fcmApiService.sendSimpleNotification(
                FcmNotificationRequest(
                    preferences.getString(Constants.FCM_TOKEN_KEY, "")!!,
                    data = FcmDataPayload(
                        "Data payload",
                        "Notification with data body",
                        "Data message"
                    ),
                    notification = FcmNotificationPayload(
                        "Composite notification",
                        "Composite notification"
                    )
                )
            )
            if (response.isSuccessful) {
                emit("Notification with data payload sent Successfully")
            } else {
                emit("Send notification failed")
            }
        } catch (e: Exception) {
            emit("Notification Exception: ${e.message}")
        }

    }

    suspend fun sendTopicNotification() = flow {
        try {
            val response = fcmApiService.sendSimpleNotification(
                FcmNotificationRequest(
                    "/topics/${Constants.TOPIC}",
                    FcmNotificationPayload("Simple topic notification", "Simple topic notification")
                )
            )
            if (response.isSuccessful) {
                emit("Notification sent Successfully")
            } else {
                emit("Send notification failed")
            }
        } catch (e: Exception) {
            emit("Notification Exception: ${e.message}")
        }

    }

}