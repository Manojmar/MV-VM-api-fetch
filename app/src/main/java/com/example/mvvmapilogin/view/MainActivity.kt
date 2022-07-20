package com.example.mvvmapilogin.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.example.mvvmapilogin.adapter.Adapter
import com.example.mvvmapilogin.databinding.ActivityMainBinding
import com.example.mvvmapilogin.network.ResponseData
import com.example.mvvmapilogin.network.User
import com.example.mvvmapilogin.viewmodal.Authviewmodal

class MainActivity : AppCompatActivity() {
    private lateinit var mainBinding: ActivityMainBinding
    private val authviewmodal: Authviewmodal by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mainBinding.root)
        authviewmodal.getcountry()
        authviewmodal.outputlist.observe(this,outputObserver)
    }

   private val outputObserver=Observer<ResponseData>{
        if(it.status_code==200)
        mainBinding.recycle.adapter= Adapter(it.data)
       Adapter.setOnItemClickListener
       startActivity(Intent(this@MainActivity,list::class.java
       ))
    }
}