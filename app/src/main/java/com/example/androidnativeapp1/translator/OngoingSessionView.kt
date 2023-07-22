package com.example.androidnativeapp1.translator

import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.hardware.Camera
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import android.widget.Button
import com.example.androidnativeapp1.R
import com.example.androidnativeapp1.utilities.CameraActivity


class OngoingSessionView : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.ongoing_session_view)


        val leaveSession: Button = findViewById(R.id.leaveSession)
        leaveSession.setOnClickListener {
            startActivity(Intent(this, CancelSessionLayout::class.java))
        }

        CameraActivity.start(this)

    }

    /** Check if this device has a camera */



}