package com.example.mvvmapilogin.network


data class ResponseData(
val status_code : Int,
val title : String,
val message: String,
val data : List<User>
)

