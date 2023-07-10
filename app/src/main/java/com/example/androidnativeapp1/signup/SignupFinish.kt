package com.example.androidnativeapp1.signup

import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import android.widget.Button
import android.widget.DatePicker
import com.example.androidnativeapp1.R
import com.example.androidnativeapp1.splash_screen.Onboarding4
import java.util.Calendar


class SignupFinish : AppCompatActivity() {

    private lateinit var selectedDateTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.signup_finish)

        val backToSignup: TextView = findViewById(R.id.backToSignup)
        backToSignup.setOnClickListener {
            startActivity(Intent(this, Signup::class.java))
        }

        val finishSignup: Button = findViewById(R.id.signupFinishButton)
        finishSignup.setOnClickListener {
            startActivity(Intent(this, SignupFinishLayout::class.java))
        }
    }




}