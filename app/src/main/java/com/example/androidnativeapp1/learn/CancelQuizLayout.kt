package com.example.androidnativeapp1.learn

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
import com.example.androidnativeapp1.home.Home
import com.example.androidnativeapp1.home.SavedTranslations
import com.example.androidnativeapp1.login.Login


class CancelQuizLayout : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        cancelQuizDialog()
    }

    private fun cancelQuizDialog() {
        val dialog = Dialog(this)
        dialog.setContentView(R.layout.cancel_quiz_layout)
        val fadeInAnimation = AlphaAnimation(0.0f, 1.0f)
        fadeInAnimation.duration = 1000
        val majorLayout = findViewById<ConstraintLayout>(R.id.majorLayout)
        majorLayout.startAnimation(fadeInAnimation)

        val backToQuiz = dialog.findViewById<Button>(R.id.backToQuiz)
        backToQuiz.setOnClickListener {
            finish()
        }
        val cancelQuiz = dialog.findViewById<Button>(R.id.cancelQuiz)
        cancelQuiz.setOnClickListener {
            dialog.dismiss()
            startActivity(Intent(this, ListOfQuizzes::class.java))
        }
        dialog.show()
    }

}