package com.example.androidnativeapp1.reset_password

import android.content.Intent
import android.os.Bundle
import android.view.animation.AlphaAnimation
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import android.widget.Button
import android.widget.EditText
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.androidnativeapp1.R
import com.example.androidnativeapp1.home.Home


class ResetPasswordEmail: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.reset_password_email)
        val fadeInAnimation = AlphaAnimation(0.0f, 1.0f)
        fadeInAnimation.duration = 1000
        val majorLayout = findViewById<ConstraintLayout>(R.id.majorLayout)
        majorLayout.startAnimation(fadeInAnimation)

        val backButton: TextView = findViewById(R.id.backButton)
        backButton.setOnClickListener {
            finish()
        }

        val resetPasswordButton: Button = findViewById(R.id.resetPasswordButton)
        resetPasswordButton.setOnClickListener {
            val email: EditText = findViewById(R.id.email)
            val errorText: TextView = findViewById(R.id.errorText)
            val regexEmail = Regex("[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,4}")
            if(regexEmail.matches(email.text) == false){
                errorText.text = "Please enter valid email !"
            }else{
                startActivity(Intent(this, ResetPasswordVerifyCode::class.java))
            }

        }

    }


}

