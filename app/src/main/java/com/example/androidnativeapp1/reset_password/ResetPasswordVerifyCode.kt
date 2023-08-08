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


class ResetPasswordVerifyCode: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.reset_password_verify_code)
        val fadeInAnimation = AlphaAnimation(0.0f, 1.0f)
        fadeInAnimation.duration = 1000
        val majorLayout = findViewById<ConstraintLayout>(R.id.majorLayout)
        majorLayout.startAnimation(fadeInAnimation)

        val backButton: TextView = findViewById(R.id.backButton)
        backButton.setOnClickListener {
            finish()
        }

        val verifyCodeButton: Button = findViewById(R.id.verifyCodeButton)
        verifyCodeButton.setOnClickListener {
            val digit1: EditText = findViewById(R.id.digit1)
            val digit2: EditText = findViewById(R.id.digit2)
            val digit3: EditText = findViewById(R.id.digit3)
            val digit4: EditText = findViewById(R.id.digit4)
            val digit5: EditText = findViewById(R.id.digit5)
            val digit6: EditText = findViewById(R.id.digit6)
            val errorText: TextView = findViewById(R.id.errorText)
            val verificationCode = digit1.text.toString()+digit2.text.toString()+digit3.text.toString()+digit4.text.toString()+digit5.text.toString()+digit6.text.toString()
            if(verificationCode.length < 6){
                errorText.text = "Please enter valid code !"
            }else{
                startActivity(Intent(this, CreateNewPassword::class.java))
            }

        }

    }


}

