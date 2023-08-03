package com.example.androidnativeapp1.signup

import android.app.DatePickerDialog
import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.animation.AlphaAnimation
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import android.widget.Button
import android.widget.DatePicker
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.androidnativeapp1.R
import com.example.androidnativeapp1.login.Login
import com.example.androidnativeapp1.splash_screen.Onboarding4
import java.util.Calendar


class SignupFinish : AppCompatActivity() {

    private lateinit var selectedDateTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.signup_finish)
        val fadeInAnimation = AlphaAnimation(0.0f, 1.0f)
        fadeInAnimation.duration = 1000
        val majorLayout = findViewById<ConstraintLayout>(R.id.majorLayout)
        majorLayout.startAnimation(fadeInAnimation)

        val backToSignup: TextView = findViewById(R.id.backToSignup)
        backToSignup.setOnClickListener {
            startActivity(Intent(this, Signup::class.java))
        }

        val goPremiumButton: Button = findViewById(R.id.goPremiumButton)
        goPremiumButton.setOnClickListener {
            startActivity(Intent(this, CheckoutActivity::class.java))
        }

        val finishSignup: Button = findViewById(R.id.signupFinishButton)
        finishSignup.setOnClickListener {
            signupFinishDialog()
        }
    }

    private fun signupFinishDialog() {
        val dialog = Dialog(this)
        dialog.setContentView(R.layout.signup_finish_layout)
        val fadeInAnimation = AlphaAnimation(0.0f, 1.0f)
        fadeInAnimation.duration = 1000
        val majorLayout = findViewById<ConstraintLayout>(R.id.majorLayout)
        majorLayout.startAnimation(fadeInAnimation)

        val signupFinishLoginButton = dialog.findViewById<Button>(R.id.signupFinishLoginButton)
        signupFinishLoginButton.setOnClickListener {
            dialog.dismiss()
            startActivity(Intent(this, Login::class.java))
        }

        dialog.show()
    }

}