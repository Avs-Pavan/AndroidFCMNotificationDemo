package com.kevin.androidfcmnotificationdemo.di

import android.content.Context
import android.content.SharedPreferences
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object PersistenceModule {

    @Provides
    @Singleton
    fun provideSharedPref(@ApplicationContext context: Context): SharedPreferences =
        context.getSharedPreferences(Constants.SHARED_PREF_NAME, Context.MODE_PRIVATE)

    @FCMTOKEN
    @Provides
    @Singleton
    fun provideFCmToken(sharedPreferences: SharedPreferences): String =
        sharedPreferences.getString(Constants.FCM_TOKEN_KEY, "") ?: ""
}