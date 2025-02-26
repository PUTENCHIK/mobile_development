package com.example.fragmentweatherapp.data

import retrofit2.http.GET
import retrofit2.http.Query

interface Request {
    @GET("/data/2.5/weather")
    suspend fun getData(
        @Query("q") q: String,
        @Query("appid") appid: String,
        @Query("units") units: String = "metric",
    ): Data
}