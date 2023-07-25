package com.example.androidnativeapp1.reset_password

import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.view.Gravity
import android.view.animation.AlphaAnimation
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import android.widget.Button
import androidx.cardview.widget.CardView
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.androidnativeapp1.R
import com.example.androidnativeapp1.home.SavedTranslations
import com.example.androidnativeapp1.login.Login


class ResetPasswordLayout : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        resetPasswordFinishDialog()

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