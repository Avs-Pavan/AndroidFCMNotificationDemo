package com.kevin.androidfcmnotificationdemo.di.api


import com.kevin.androidfcmnotificationdemo.dto.FcmNotificationRequest
import com.kevin.androidfcmnotificationdemo.dto.FcmResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface FCMApiService {

    @POST("send")
    suspend fun sendSimpleNotification(@Body fcmNotificationRequest: FcmNotificationRequest): Response<FcmResponse>

}