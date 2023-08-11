package com.example.androidnativeapp1.translator

import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.SystemClock
import android.view.animation.AlphaAnimation
import androidx.appcompat.app.AppCompatActivity
import android.widget.Button
import android.widget.TextView

import androidx.constraintlayout.widget.ConstraintLayout

import androidx.core.app.ActivityCompat
import com.example.androidnativeapp1.R
import com.example.androidnativeapp1.home.Home


class SessionStartView : AppCompatActivity() {
    val handler = Handler()
    val runnable = Runnable {
        /*val intent = Intent(this, SessionStartCountdown::class.java)
        intent.putExtra("sessionID", intent.getStringExtra("sessionID"))
        startActivity(intent) */
        startActivity(Intent(this, SessionStartCountdown::class.java).putExtra("sessionID", intent.getStringExtra("sessionID")))
    }
    val timeoutDuration = 3000L
    var remainingTime = 0L
    var startTime = 0L
    var convertedSessionID = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.session_start_view)
        val fadeInAnimation = AlphaAnimation(0.0f, 1.0f)
        fadeInAnimation.duration = 1000
        val majorLayout = findViewById<ConstraintLayout>(R.id.majorLayout)
        majorLayout.startAnimation(fadeInAnimation)

        var sessionID = intent.getStringExtra("sessionID")
        val parts = intent.getStringExtra("sessionID")?.split("-")
        if (parts?.size == 5) {
            convertedSessionID = "${parts[0].take(3)}-${parts[1].take(3)}-${parts[2].take(3)}"
        }
        val sessionIdTextView = findViewById<TextView>(R.id.sessionIdTextView)
        sessionIdTextView.text = convertedSessionID.uppercase()


        handler.removeCallbacks(runnable)
        startTime = SystemClock.uptimeMillis()
        handler.postDelayed(runnable, timeoutDuration)


        val leaveSession: Button = findViewById(R.id.leaveSession)
        leaveSession.setOnClickListener {
            val currentTime = SystemClock.uptimeMillis()
            remainingTime = currentTime - startTime
            handler.removeCallbacks(runnable)
            cancelSessionDialog()
        }


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
            handler.postDelayed(runnable, remainingTime)
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

}

// Check if all permissions are granted


