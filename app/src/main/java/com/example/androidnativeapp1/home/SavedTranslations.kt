package com.example.androidnativeapp1.home

import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import android.widget.Button
import android.widget.ImageView
import androidx.cardview.widget.CardView
import com.example.androidnativeapp1.LeftDrawerLayout
import com.example.androidnativeapp1.R
import com.example.androidnativeapp1.databinding.ViewTranslationLayoutBinding
import com.google.android.material.bottomnavigation.BottomNavigationView


class SavedTranslations : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.saved_translations)

        val backToHome: TextView = findViewById(R.id.backToHome)
        backToHome.setOnClickListener {
            startActivity(Intent(this, Home::class.java))
        }

        val viewTranslationLayout: CardView = findViewById(R.id.viewTranslationLayout)
        viewTranslationLayout.setOnClickListener {
            startActivity(Intent(this, ViewTranslationLayout::class.java))
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