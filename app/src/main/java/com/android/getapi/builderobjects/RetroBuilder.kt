package com.android.getapi.builderobjects

import android.text.TextUtils
import com.android.getapi.retroapi.ApiService

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RetroBuilder {


    private var requestTimeout = 60
    var authToken = ""

    private var retrofit : Retrofit? = null
    private var okHttpClient: OkHttpClient? = null

    const val APP_VERSION = "1.0"
    const val DEVICE_TYPE = "A"
    const val APPLICATION_JSON = "application/json"

    // Create Service
    val service = GetDetail().create(ApiService::class.java)

    val serviceLogin = UserLogin().create(ApiService::class.java)

    fun getApiServices(): ApiService? {
        return service
    }

    // Create Retrofit Client
    fun GetDetail(): Retrofit {

        if (okHttpClient == null)
            initOkHttp()

        if (retrofit == null) {
            retrofit = Retrofit.Builder()
                .baseUrl("https://simplifiedcoding.net/demos/")
                .client(okHttpClient!!)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create()).build()
        }

        return retrofit!!
    }


    fun UserLogin(): Retrofit {

        if (okHttpClient == null)
            initOkHttp()

        if (retrofit == null) {
            retrofit = Retrofit.Builder()
                .baseUrl("https://simplifiedcoding.net/demos/")
                .client(okHttpClient!!)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create()).build()
        }

        return retrofit!!
    }







    private fun initOkHttp() {
        val httpClient = OkHttpClient().newBuilder()
            .connectTimeout(requestTimeout.toLong(), TimeUnit.SECONDS)
            .readTimeout(requestTimeout.toLong(), TimeUnit.SECONDS)
            .writeTimeout(requestTimeout.toLong(), TimeUnit.SECONDS)

        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        httpClient.addInterceptor(interceptor)

        httpClient.addInterceptor { chain ->
            val original = chain.request()
            val requestBuilder = original.newBuilder()
                .addHeader("appversion", APP_VERSION)
                .addHeader("Content-Type", APPLICATION_JSON)
                .addHeader("devicetype", DEVICE_TYPE)
                .addHeader("Accept", APPLICATION_JSON)

            if (!TextUtils.isEmpty(authToken)) {
                requestBuilder.addHeader("Authorization","Token "+ authToken)
            }

            val request = requestBuilder.build()
            chain.proceed(request)
        }

        okHttpClient = httpClient.build()
    }


/*
    // https://simplifiedcoding.net/demos/marvel/

        fun GetDetail(): Retrofit {
            return Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("https://simplifiedcoding.net/demos/")
                .build()
        }*/

    //http://192.168.2.106:3000/login

    /*fun UserLogin(): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("http://192.168.2.106:3000/")
            .build()
    }
*/
    }





