package com.rebin.solulabtest.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.gson.Gson
import com.rebin.solulabtest.network.Repository
import com.rebin.solulabtest.network.ResponseModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.Exception

class HomefragmentViewModel : ViewModel() {

    var mresponse= MutableLiveData<ResponseModel>()
    var merror= MutableLiveData<String>()

    var gson= Gson()
    fun getdata() {
        CoroutineScope(Dispatchers.IO).launch{
            try {
                val call = Repository.getdata()
                if (call != null) {
                    call.apply {
                        mresponse.postValue(this)
                    }
                } else {
                    merror.postValue("Something went wrong")
                    throw Exception("Something went wrong")
                }
            }catch (e: Exception){
                Log.e("jkdkaskj",e.message.toString())

                merror.postValue(e.message)
            }
        }
    }

}