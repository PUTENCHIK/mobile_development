package com.example.fragmentweatherapp

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface Request {
    @GET("/data/2.5/weather")
    suspend fun getData(
        @Query("q") q: String = "Irkutsk",
        @Query("appid") appid: String,
        @Query("units") units: String = "metric",
    ): Data
}