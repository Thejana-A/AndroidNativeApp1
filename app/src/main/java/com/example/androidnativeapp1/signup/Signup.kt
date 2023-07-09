package com.example.androidnativeapp1

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import android.widget.Button


class Signup : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.signup)

        val backToOnboarding4: TextView = findViewById(R.id.backToOnboarding4)
        backToOnboarding4.setOnClickListener {
            startActivity(Intent(this, Onboarding4::class.java))
        }

        val continueSignup: Button = findViewById(R.id.continueSignup)
        continueSignup.setOnClickListener {
            //startActivity(Intent(this, SignupFinish::class.java))
        }
    }


}