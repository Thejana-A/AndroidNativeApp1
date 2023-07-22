package com.example.androidnativeapp1.translator

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import android.widget.Button
import androidx.core.app.ActivityCompat
import com.example.androidnativeapp1.R


class SessionStartView : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.session_start_view)

        val handler = Handler()
        val runnable = Runnable {
            startActivity(Intent(this, SessionStartCountdown::class.java))
        }
        val timeoutDuration = 3000L
        handler.postDelayed(runnable, timeoutDuration)

        val leaveSession: Button = findViewById(R.id.leaveSession)
        leaveSession.setOnClickListener {
            startActivity(Intent(this, CancelSessionLayout::class.java))
        }


    }

    // Check if all permissions are granted



}

