package com.example.androidnativeapp1.reset_password

import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.view.animation.AlphaAnimation
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import android.widget.Button
import android.widget.EditText
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.androidnativeapp1.R
import com.example.androidnativeapp1.login.Login
import com.example.androidnativeapp1.signup.SignupFinish


class CreateNewPassword: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.create_new_password)
        val fadeInAnimation = AlphaAnimation(0.0f, 1.0f)
        fadeInAnimation.duration = 1000
        val majorLayout = findViewById<ConstraintLayout>(R.id.majorLayout)
        majorLayout.startAnimation(fadeInAnimation)

        val backButton: TextView = findViewById(R.id.backButton)
        backButton.setOnClickListener {
            finish()
        }

        val createNewPasswordButton: Button = findViewById(R.id.createNewPasswordButton)
        createNewPasswordButton.setOnClickListener {
            val password: EditText = findViewById(R.id.password)
            val confirmPassword: EditText = findViewById(R.id.confirmPassword)
            val passwordText = password.text.toString()
            val confirmPasswordText = confirmPassword.text.toString()
            val errorText: TextView = findViewById(R.id.errorText)
            if((password.text).length <= 8){
                errorText.text = "Password should have more than 8 characters !"
            }else if(!(passwordText.equals(confirmPasswordText))){
                errorText.text = "Confirm password correctly !"
            }else{
                resetPasswordFinishDialog()
            }

        }
    }


    private fun resetPasswordFinishDialog() {
        val dialog = Dialog(this)
        dialog.setContentView(R.layout.reset_password_layout)
        val fadeInAnimation = AlphaAnimation(0.0f, 1.0f)
        fadeInAnimation.duration = 1000
        val majorLayout = findViewById<ConstraintLayout>(R.id.majorLayout)
        majorLayout.startAnimation(fadeInAnimation)

        val resetPasswordLoginButton = dialog.findViewById<Button>(R.id.resetPasswordLoginButton)
        resetPasswordLoginButton.setOnClickListener {
            dialog.dismiss()
            startActivity(Intent(this, Login::class.java))
        }

        dialog.show()
    }

}