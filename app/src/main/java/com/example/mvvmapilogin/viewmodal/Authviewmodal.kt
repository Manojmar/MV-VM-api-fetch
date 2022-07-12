package com.example.mvvmapilogin.viewmodal


import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.mvvmapilogin.network.ResponseData
import com.example.mvvmapilogin.network.User


class Authviewmodal:ViewModel(){
    private val authorepository=Authorepository()

    val responseData:LiveData<ResponseData> by lazy { authorepository.responseData}
    val error:LiveData<String> by lazy { authorepository.error }

    val outputlist:LiveData<List<User>> by lazy { authorepository.outputlist }

    fun login(email:String,pass:String){
     authorepository.login(email=email, pass=pass)
    }

    fun getcountry(){
        authorepository.getCountry()
    }
}

