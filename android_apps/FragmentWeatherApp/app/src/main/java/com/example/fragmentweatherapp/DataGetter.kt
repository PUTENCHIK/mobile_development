package com.example.fragmentweatherapp

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object DataGetter {
    private const val BASE_URL = "https://api.openweathermap.org"

    val request: Request by lazy {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        retrofit.create(Request::class.java)
    }
}