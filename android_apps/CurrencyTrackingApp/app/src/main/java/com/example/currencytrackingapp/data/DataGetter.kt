package com.example.currencytrackingapp.data

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object DataGetter {
    private const val BASE_URL = "https://min-api.cryptocompare.com"

    val request: Request by lazy {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        retrofit.create(Request::class.java)
    }
}