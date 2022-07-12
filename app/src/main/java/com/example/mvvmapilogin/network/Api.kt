package com.example.mvvmapilogin.network


import com.google.gson.GsonBuilder
import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface Api {

    @POST("login1.php")
    fun login(@Body params: JsonObject): Call<ResponseData>

    @GET("select.php")
    fun imgcount():Call<ResponseData>

    companion object{
        var gson= GsonBuilder()
            .setLenient()
            .create()

        operator fun invoke():Api{
            return Retrofit.Builder()
                .baseUrl("https://macappstudiotraining.com/manickaraj/api/products/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()
                .create(Api::class.java)
        }
    }
}