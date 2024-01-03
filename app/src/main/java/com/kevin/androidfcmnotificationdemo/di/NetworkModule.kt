package com.kevin.androidfcmnotificationdemo.di


import com.kevin.androidfcmnotificationdemo.di.api.FCMApiService
import com.kevin.androidfcmnotificationdemo.utils.ServerKeyInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideApiUrl(): String = Constants.BASE_URL

    @ServerKey
    @Provides
    @Singleton
    fun provideServerKey(): String = Constants.SERVER_KEY


    @Provides
    @Singleton
    fun provideServerKeyInterceptor(@ServerKey serverKey: String): ServerKeyInterceptor =
        ServerKeyInterceptor(serverKey)

    @Provides
    @Singleton
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor =
        HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

    @Provides
    @Singleton
    fun provideOkHttpClient(
        httpLoggingInterceptor: HttpLoggingInterceptor,
        serverKeyInterceptor: ServerKeyInterceptor
    ): OkHttpClient = OkHttpClient.Builder()
        .addInterceptor(httpLoggingInterceptor)
        .addInterceptor(serverKeyInterceptor)
        .build()


    @Provides
    @Singleton
    fun provideRetrofit(
        baseUrl: String,
        okHttpClient: OkHttpClient
    ): Retrofit = Retrofit.Builder()
        .baseUrl(baseUrl)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): FCMApiService =
        retrofit.create(FCMApiService::class.java)
}