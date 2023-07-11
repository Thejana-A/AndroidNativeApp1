package com.example.androidnativeapp1.translator

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
import com.example.androidnativeapp1.learn.ListOfQuizzes
import com.example.androidnativeapp1.login.Login


class CancelSessionLayout : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        cancelSessionDialog()
    }

    private fun cancelSessionDialog() {
        val dialog = Dialog(this)
        dialog.setContentView(R.layout.cancel_session_layout)

        val continueSessionButton = dialog.findViewById<Button>(R.id.continueSessionButton)
        continueSessionButton.setOnClickListener {
            finish()
        }

        val cancelSessionButton = dialog.findViewById<Button>(R.id.cancelSessionButton)
        cancelSessionButton.setOnClickListener {
            dialog.dismiss()
            startActivity(Intent(this, SessionCompletedLayout::class.java))
        }
        dialog.show()
    }

}