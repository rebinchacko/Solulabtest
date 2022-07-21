package com.rebin.solulabtest.network


object Repository {

    suspend fun getdata() = ApiClient.create().getdata()

}