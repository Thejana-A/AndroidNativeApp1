package com.example.androidnativeapp1

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import android.widget.Button


class Onboarding3 : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.onboarding3)

        val skipToOnboarding4: TextView = findViewById(R.id.skipToOnboarding4)
        skipToOnboarding4.setOnClickListener {
            startActivity(Intent(this, Onboarding4::class.java))
        }

        val linkToOnboarding4: Button = findViewById(R.id.linkToOnboarding4)
        linkToOnboarding4.setOnClickListener {
            startActivity(Intent(this, Onboarding4::class.java))
        }
    }


}