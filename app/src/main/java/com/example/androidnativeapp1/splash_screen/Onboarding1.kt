package com.example.androidnativeapp1.splash_screen

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import android.widget.Button
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.androidnativeapp1.R
import android.transition.ChangeBounds
import android.transition.TransitionManager


class Onboarding1 : AppCompatActivity() {

    private lateinit var container: ConstraintLayout
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.onboarding1)

        val skipToOnboarding4: TextView = findViewById(R.id.skipToOnboarding4)
        skipToOnboarding4.setOnClickListener {
            startActivity(Intent(this, Onboarding4::class.java))
        }

        val linkToOnboarding2: Button = findViewById(R.id.linkToOnboarding2)
        linkToOnboarding2.setOnClickListener {
            startActivity(Intent(this, Onboarding2::class.java))
        }
    }


}