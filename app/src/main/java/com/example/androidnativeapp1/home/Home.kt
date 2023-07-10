package com.example.androidnativeapp1.home

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.Button
import android.widget.ImageView
import com.example.androidnativeapp1.LeftDrawerLayout
import com.example.androidnativeapp1.R
import com.google.android.material.bottomnavigation.BottomNavigationView


class Home : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.home)

        val startSession: Button = findViewById(R.id.startSession)
        startSession.setOnClickListener {
            //startActivity(Intent(this, ScanQrCode::class.java))
        }

        val savedTranslations: Button = findViewById(R.id.savedTranslations)
        savedTranslations.setOnClickListener {
            startActivity(Intent(this, SavedTranslations::class.java))
        }

        val leftDrawerIcon: ImageView = findViewById(R.id.leftDrawerIcon)
        leftDrawerIcon.setOnClickListener {
            startActivity(Intent(this, LeftDrawerLayout::class.java))
        }

        val bottomNavigationView: BottomNavigationView = findViewById(R.id.bottom_navigation)
        bottomNavigationView.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.home_tab -> {
                    setContentView(R.layout.home)
                    true
                }
                R.id.camera_tab -> {
                    setContentView(R.layout.scan_qr_code)
                    true
                }
                R.id.learn_tab -> {
                    setContentView(R.layout.list_of_lessons)
                    true
                }
                R.id.chat_tab -> {
                    setContentView(R.layout.chat_initial_page)
                    true
                }
                else -> false
            }
        }

    }


}