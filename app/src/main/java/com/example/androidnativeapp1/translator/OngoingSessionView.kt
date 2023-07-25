package com.example.androidnativeapp1.translator

import android.Manifest
import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.hardware.Camera
import android.os.Bundle
import android.os.Handler
import android.view.animation.AlphaAnimation
import androidx.appcompat.app.AppCompatActivity
import android.widget.Button
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.androidnativeapp1.R
import com.example.androidnativeapp1.home.Home
import com.example.androidnativeapp1.utilities.CameraActivity


class OngoingSessionView : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.ongoing_session_view)
        val fadeInAnimation = AlphaAnimation(0.0f, 1.0f)
        fadeInAnimation.duration = 1000
        val majorLayout = findViewById<ConstraintLayout>(R.id.majorLayout)
        majorLayout.startAnimation(fadeInAnimation)


        val leaveSession: Button = findViewById(R.id.leaveSession)
        leaveSession.setOnClickListener {
            cancelSessionDialog()
            //startActivity(Intent(this, CancelSessionLayout::class.java))
        }

        CameraActivity.start(this)

    }

    private fun cancelSessionDialog() {
        val dialog = Dialog(this)
        dialog.setContentView(R.layout.cancel_session_layout)
        val fadeInAnimation = AlphaAnimation(0.0f, 1.0f)
        fadeInAnimation.duration = 1000
        val majorLayout = findViewById<ConstraintLayout>(R.id.majorLayout)
        majorLayout.startAnimation(fadeInAnimation)

        val continueSessionButton = dialog.findViewById<Button>(R.id.continueSessionButton)
        continueSessionButton.setOnClickListener {
            dialog.dismiss()
        }

        val cancelSessionButton = dialog.findViewById<Button>(R.id.cancelSessionButton)
        cancelSessionButton.setOnClickListener {
            dialog.dismiss()
            sessionCompletedDialog()
        }
        dialog.show()
    }

    private fun sessionCompletedDialog() {
        val dialog = Dialog(this)
        dialog.setContentView(R.layout.session_completed_layout)
        val fadeInAnimation = AlphaAnimation(0.0f, 1.0f)
        fadeInAnimation.duration = 1000
        val majorLayout = findViewById<ConstraintLayout>(R.id.majorLayout)
        majorLayout.startAnimation(fadeInAnimation)

        val backToHomeButton = dialog.findViewById<Button>(R.id.backToHomeButton)
        backToHomeButton.setOnClickListener {
            dialog.dismiss()
            startActivity(Intent(this, Home::class.java))
        }
        dialog.show()
    }

    /** Check if this device has a camera */


}
