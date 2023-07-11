package com.example.androidnativeapp1.learn

import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.view.Gravity
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import android.widget.Button
import androidx.cardview.widget.CardView
import com.example.androidnativeapp1.R
import com.example.androidnativeapp1.home.Home
import com.example.androidnativeapp1.home.SavedTranslations
import com.example.androidnativeapp1.login.Login


class QuizCompletedLayout : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        quizCompletedDialog()

    }

    private fun quizCompletedDialog() {
        val dialog = Dialog(this)
        dialog.setContentView(R.layout.quiz_completed_layout)

        val backToHomeButton = dialog.findViewById<Button>(R.id.backToHomeButton)
        backToHomeButton.setOnClickListener {
            dialog.dismiss()
            startActivity(Intent(this, Home::class.java))
        }
        dialog.show()
    }

}