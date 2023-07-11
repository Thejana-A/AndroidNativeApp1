package com.example.androidnativeapp1.translator

import android.content.Intent
import android.os.Bundle
import android.os.Handler
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.session_start_countdown)

        val handler = Handler()
        val runnable = Runnable {
            startActivity(Intent(this, OngoingSessionView::class.java))
        }
        val timeoutDuration = 3000L
        handler.postDelayed(runnable, timeoutDuration)

        val leaveSession: Button = findViewById(R.id.leaveSession)
        leaveSession.setOnClickListener {
            startActivity(Intent(this, CancelSessionLayout::class.java))
        }

    }


}