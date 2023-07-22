package com.example.androidnativeapp1.translator

import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.SystemClock
import androidx.appcompat.app.AppCompatActivity
import android.widget.Button
import androidx.core.app.ActivityCompat
import com.example.androidnativeapp1.R


class SessionStartView : AppCompatActivity() {
    val handler = Handler()
    val runnable = Runnable {
        startActivity(Intent(this, SessionStartCountdown::class.java))
    }
    val timeoutDuration = 3000L
    var remainingTime = 0L
    var startTime = 0L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.session_start_view)

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

        val continueSessionButton = dialog.findViewById<Button>(R.id.continueSessionButton)
        continueSessionButton.setOnClickListener {
            dialog.dismiss()
            handler.postDelayed(runnable, remainingTime)
        }

        val cancelSessionButton = dialog.findViewById<Button>(R.id.cancelSessionButton)
        cancelSessionButton.setOnClickListener {
            dialog.dismiss()
            startActivity(Intent(this, SessionCompletedLayout::class.java))
        }
        dialog.show()
    }



}

