package com.kevin.androidfcmnotificationdemo.di

import android.content.SharedPreferences
import com.kevin.androidfcmnotificationdemo.FcmRepository
import com.kevin.androidfcmnotificationdemo.di.api.FCMApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideFcmRepository(
        fcmApiService: FCMApiService,
        sharedPreferences: SharedPreferences,
    ): FcmRepository =
        FcmRepository(fcmApiService, sharedPreferences)
}