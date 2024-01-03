package com.kevin.androidfcmnotificationdemo.utils

import com.kevin.androidfcmnotificationdemo.di.ServerKey
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class ServerKeyInterceptor @Inject constructor(@ServerKey private val serverKey: String) :
    Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {

        val newRequest = chain.request().newBuilder()
            .header("Content-Type", "application/json")
            .header("Authorization", "key=$serverKey")
            .build()
        return chain.proceed(newRequest)
    }

}
