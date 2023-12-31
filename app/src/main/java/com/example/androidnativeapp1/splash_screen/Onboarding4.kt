package com.example.androidnativeapp1.splash_screen

import android.content.Intent
import android.os.Bundle
import android.view.animation.AlphaAnimation
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import android.widget.Button
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.androidnativeapp1.R
import com.example.androidnativeapp1.login.Login
import com.example.androidnativeapp1.signup.Signup


class Onboarding4 : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.onboarding4)
        val fadeInAnimation = AlphaAnimation(0.0f, 1.0f)
        fadeInAnimation.duration = 1000
        val majorLayout = findViewById<ConstraintLayout>(R.id.majorLayout)
        majorLayout.startAnimation(fadeInAnimation)

        val linkToLogin: Button = findViewById(R.id.linkToLogin)
        linkToLogin.setOnClickListener {
            startActivity(Intent(this, Login::class.java))
        }

        val linkToSignup: Button = findViewById(R.id.linkToSignup)
        linkToSignup.setOnClickListener {
            startActivity(Intent(this, Signup::class.java))
        }
    }


}