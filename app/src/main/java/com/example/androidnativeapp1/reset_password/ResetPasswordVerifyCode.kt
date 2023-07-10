package com.example.androidnativeapp1.reset_password

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import android.widget.Button
import com.example.androidnativeapp1.R


class ResetPasswordVerifyCode: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.reset_password_verify_code)

        val backButton: TextView = findViewById(R.id.backButton)
        backButton.setOnClickListener {
            finish()
        }

        val verifyCodeButton: Button = findViewById(R.id.verifyCodeButton)
        verifyCodeButton.setOnClickListener {
            startActivity(Intent(this, CreateNewPassword::class.java))
        }

    }


}

