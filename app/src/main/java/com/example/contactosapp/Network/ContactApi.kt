package com.example.contactosapp.Network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ContactApi {

    private val retrofit = Retrofit.Builder()
        .baseUrl("http://192.168.1.141:3000/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val service = retrofit.create(ContactService::class.java)
}