package com.example.currencytrackingapp.data

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface Request {
    @GET("/data/price")
    suspend fun getData(
        @Query("fsym") fsym: String,
        @Query("tsyms") tsyms: String
    ): Response<ResponseData>
}