package com.example.mvvmapilogin.view


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import androidx.activity.viewModels
import com.example.mvvmapilogin.databinding.ActivityLoginBinding
import com.example.mvvmapilogin.network.ResponseData
import com.example.mvvmapilogin.viewmodal.Authviewmodal
import androidx.lifecycle.Observer

class login : AppCompatActivity() {

    private lateinit var loginBinding: ActivityLoginBinding
    private val authviewmodal: Authviewmodal by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        loginBinding= ActivityLoginBinding.inflate(layoutInflater)
        setContentView(loginBinding.root)

        authviewmodal.responseData.observe(this,responseObserver)
        authviewmodal.error.observe(this,errorObserver)

        loginBinding.apply {
            btn.setOnClickListener {

                val email=loginBinding.email.text.toString()
                val pass=loginBinding.pass.text.toString()
                when {
                    email.isEmpty() || pass.isEmpty() ->{
                        Toast.makeText(this@login, "Required field is Empty", Toast.LENGTH_SHORT).show()
                    }
                    !Patterns.EMAIL_ADDRESS.matcher(email).matches()  ->{
                        Toast.makeText(this@login, "Email Not Matches", Toast.LENGTH_SHORT).show()
                    }
                    pass.length < 6 ->{
                        Toast.makeText(this@login, "Minimum six characters", Toast.LENGTH_SHORT).show()
                    }
                    else->{
                        authviewmodal.login(email,pass)
                    }
                }
            }
        }
    }

    private val responseObserver =Observer<ResponseData> {
        if (it != null) {
            if (it.status_code == 200) {
                startActivity(Intent(applicationContext, MainActivity::class.java))
            } else {
                Toast.makeText(this, "Error Accrued", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private val errorObserver = Observer<String> {
        finish()
    }
}


