package com.example.androidnativeapp1.splash_screen

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import android.widget.Button
import com.example.androidnativeapp1.R


class Onboarding2 : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.onboarding2)

        val skipToOnboarding4: TextView = findViewById(R.id.skipToOnboarding4)
        skipToOnboarding4.setOnClickListener {
            startActivity(Intent(this, Onboarding4::class.java))
        }

        val linkToOnboarding3: Button = findViewById(R.id.linkToOnboarding3)
        linkToOnboarding3.setOnClickListener {
            startActivity(Intent(this, Onboarding3::class.java))
        }
    }


}