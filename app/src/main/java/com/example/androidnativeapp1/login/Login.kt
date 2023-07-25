package com.example.androidnativeapp1.login

import android.content.Intent
import android.os.Bundle
import android.view.animation.AlphaAnimation
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import android.widget.Button
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.androidnativeapp1.R
import com.example.androidnativeapp1.home.Home
import com.example.androidnativeapp1.reset_password.ResetPasswordEmail
import com.example.androidnativeapp1.signup.Signup
import com.example.androidnativeapp1.splash_screen.Onboarding4

class Login : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login)
        val fadeInAnimation = AlphaAnimation(0.0f, 1.0f)
        fadeInAnimation.duration = 1000
        val majorLayout = findViewById<ConstraintLayout>(R.id.majorLayout)
        majorLayout.startAnimation(fadeInAnimation)

        val backToOnboarding4: TextView = findViewById(R.id.backToOnboarding4)
        backToOnboarding4.setOnClickListener {
            startActivity(Intent(this, Onboarding4::class.java))
        }

        val resetForgotPassword: TextView = findViewById(R.id.resetForgotPassword)
        resetForgotPassword.setOnClickListener {
            startActivity(Intent(this, ResetPasswordEmail::class.java))
        }

        val loginButton: Button = findViewById(R.id.loginButton)
        loginButton.setOnClickListener {
            startActivity(Intent(this, Home::class.java))
        }

        val linkToSignUp: TextView = findViewById(R.id.linkToSignUp)
        linkToSignUp.setOnClickListener {
            startActivity(Intent(this, Signup::class.java))
        }

    }


}