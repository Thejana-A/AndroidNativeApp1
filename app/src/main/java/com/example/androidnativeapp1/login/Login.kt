package com.example.androidnativeapp1

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import android.widget.Button


class Login : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login)

        val backToOnboarding4: TextView = findViewById(R.id.backToOnboarding4)
        backToOnboarding4.setOnClickListener {
            startActivity(Intent(this, Onboarding4::class.java))
        }

        val loginButton: Button = findViewById(R.id.loginButton)
        loginButton.setOnClickListener {
            //startActivity(Intent(this, Home::class.java))
        }

        val linkToSignUp: TextView = findViewById(R.id.linkToSignUp)
        linkToSignUp.setOnClickListener {
            //startActivity(Intent(this, Signup::class.java))
        }

    }


}