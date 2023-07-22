package com.example.androidnativeapp1.translator

import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.SystemClock
import androidx.appcompat.app.AppCompatActivity
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.cardview.widget.CardView
import com.example.androidnativeapp1.LeftDrawerLayout
import com.example.androidnativeapp1.R
import com.example.androidnativeapp1.home.Home
import com.example.androidnativeapp1.learn.ListOfLessons
import com.example.androidnativeapp1.learn.OngoingQuiz
import com.example.androidnativeapp1.video_chat.ChatInitialPage
import com.example.androidnativeapp1.video_chat.ChatList
import com.example.androidnativeapp1.video_chat.ChatOptionsLayout
import com.google.android.material.bottomnavigation.BottomNavigationView


class SessionStartCountdown : AppCompatActivity() {
    val handler = Handler()
    val runnable = Runnable {
        startActivity(Intent(this, OngoingSessionView::class.java))
    }
    val timeoutDuration = 3000L
    var remainingTime = 0L
    var startTime = 0L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.session_start_countdown)

        handler.removeCallbacks(runnable)
        startTime = SystemClock.uptimeMillis()
        val countdownTimer = findViewById<TextView>(R.id.countdownTimer)
        countdownTimer.text = "03"
        handler.postDelayed( {countdownTimer.text = "02"} , 1000)
        handler.postDelayed( {countdownTimer.text = "01"} , 2000)
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
            val countdownTimer = findViewById<TextView>(R.id.countdownTimer)
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