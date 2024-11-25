package com.example.composelogin.api

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    private val client = OkHttpClient.Builder()
        .addInterceptor(TokenInterceptor)
        .build()

    private val retrofit = Retrofit.Builder()
        .client(client)
        .baseUrl("https://5249-idx-studdy-1720965967101.cluster-7ubberrabzh4qqy2g4z7wgxuw2.cloudworkstations.dev/")
        .addConverterFactory(GsonConverterFactory.create(GsonBuilder().setLenient().create()))
        .build()

    val loginService: LoginService = retrofit.create(LoginService::class.java)
}