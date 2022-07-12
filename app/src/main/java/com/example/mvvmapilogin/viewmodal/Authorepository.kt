package com.example.mvvmapilogin.viewmodal

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.mvvmapilogin.network.Api
import com.example.mvvmapilogin.network.ResponseData
import com.example.mvvmapilogin.network.User
import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Authorepository {

    val responseData = MutableLiveData<ResponseData>()
    val error = MutableLiveData<String>()

    val outputlist=MutableLiveData<List<User>>()

    fun login(email: String, pass: String) {
        val jsonObject = JsonObject()
        try {
            jsonObject.apply {
                addProperty("email", email)
                addProperty("password", pass)
            }

            Api().login(jsonObject).enqueue(object : Callback<ResponseData> {
                /*Responce Varathanu pake*/
                override fun onResponse(
                    call: Call<ResponseData>,
                    response: Response<ResponseData>
                ) {
                    if (response.isSuccessful) {
                        responseData.value = response.body()
                    }
                }

                /*Not Connected*/
                override fun onFailure(call: Call<ResponseData>, t: Throwable) {
                    error.value = t.message
                }
            })
            /*Call Ah pokathuku*/
        } catch (exc: Exception) {
            Log.e("exc", exc.toString())
        }
    }

    fun getCountry() {
        Api().imgcount().enqueue(object : Callback<ResponseData> {
            override fun onResponse(call: Call<ResponseData>, response: Response<ResponseData>) {
                if(response.isSuccessful) {
                  outputlist.value  = response.body()!!.data
                }
            }

            override fun onFailure(call: Call<ResponseData>, t: Throwable) {
                Log.d("failure", t.toString())
            }

        })
    }


}