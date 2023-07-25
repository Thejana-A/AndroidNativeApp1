package com.example.androidnativeapp1.home

import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.view.animation.AlphaAnimation
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import android.widget.Button
import androidx.cardview.widget.CardView
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.androidnativeapp1.R


class ViewTranslationLayout : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewSavedTranslationDialog()

    }

    private fun viewSavedTranslationDialog() {
        val dialog = Dialog(this)
        dialog.setContentView(R.layout.view_translation_layout)
        val fadeInAnimation = AlphaAnimation(0.0f, 1.0f)
        fadeInAnimation.duration = 1000
        val majorLayout = findViewById<ConstraintLayout>(R.id.majorLayout)
        majorLayout.startAnimation(fadeInAnimation)

        val closeSavedTranslationButton = dialog.findViewById<TextView>(R.id.closeSavedTranslationButton)
        closeSavedTranslationButton.setOnClickListener {
            dialog.dismiss()
            startActivity(Intent(this, SavedTranslations::class.java))
        }
        dialog.show()
    }

}