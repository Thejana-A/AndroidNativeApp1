package com.example.androidnativeapp1.splash_screen

import android.content.Intent
import android.os.Bundle
import android.view.animation.AlphaAnimation
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import android.widget.Button
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.androidnativeapp1.R


class Onboarding2 : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.onboarding2)
        val fadeInAnimation = AlphaAnimation(0.0f, 1.0f)
        fadeInAnimation.duration = 1000
        val majorLayout = findViewById<ConstraintLayout>(R.id.majorLayout)
        majorLayout.startAnimation(fadeInAnimation)

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