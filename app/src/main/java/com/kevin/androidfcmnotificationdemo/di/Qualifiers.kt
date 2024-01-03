package com.kevin.androidfcmnotificationdemo.di

import javax.inject.Qualifier


@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class ServerKey


@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class FCMTOKEN