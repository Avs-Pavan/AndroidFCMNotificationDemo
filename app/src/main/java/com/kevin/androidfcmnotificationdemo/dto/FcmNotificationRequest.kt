package com.kevin.androidfcmnotificationdemo.dto

data class FcmNotificationRequest(
    val to: String,
    val notification: FcmNotificationPayload? = null,
    val data: FcmDataPayload? = null
)