package com.example.androidnativeapp1.video_chat

import android.content.Intent
import android.os.Bundle
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
import com.example.androidnativeapp1.translator.ScanQrCode
import com.example.androidnativeapp1.video_chat.ChatInitialPage
import com.example.androidnativeapp1.video_chat.ChatOptionsLayout
import com.google.android.material.bottomnavigation.BottomNavigationView


class Chat : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.chat)

        val viewChatConversation: ImageView = findViewById(R.id.backButton)
        viewChatConversation.setOnClickListener {
            startActivity(Intent(this, ChatList::class.java))
        }

        val viewChatOptions: LinearLayout = findViewById(R.id.viewChatOptions)
        viewChatOptions.setOnClickListener {
            startActivity(Intent(this, ChatOptionsLayout::class.java))
        }


        val bottomNavigationView: BottomNavigationView = findViewById(R.id.bottom_navigation)
        bottomNavigationView.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.home_tab -> {
                    startActivity(Intent(this, Home::class.java))
                    true
                }
                R.id.camera_tab -> {
                    startActivity(Intent(this, ScanQrCode::class.java))
                    true
                }
                R.id.learn_tab -> {
                    startActivity(Intent(this, ListOfLessons::class.java))
                    true
                }
                R.id.chat_tab -> {
                    startActivity(Intent(this, ChatInitialPage::class.java))
                    true
                }
                else -> false
            }
        }
        bottomNavigationView.getMenu().getItem(3).setChecked(true)

    }


}