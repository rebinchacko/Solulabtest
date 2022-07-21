package com.rebin.solulabtest.network

import retrofit2.http.GET
import retrofit2.http.Query

interface Api {
    @GET("coinlist")
    suspend fun getdata():ResponseModel

}