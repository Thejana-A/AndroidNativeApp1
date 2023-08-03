package com.example.androidnativeapp1.video_chat

import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.view.Gravity
import android.view.animation.AlphaAnimation
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.androidnativeapp1.R
import com.example.androidnativeapp1.home.Home
import com.example.androidnativeapp1.learn.ListOfLessons
import com.example.androidnativeapp1.translator.ScanQrCode
import com.google.android.material.bottomnavigation.BottomNavigationView

class Chat : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.chat)
        val fadeInAnimation = AlphaAnimation(0.0f, 1.0f)
        fadeInAnimation.duration = 1000
        val majorLayout = findViewById<ConstraintLayout>(R.id.majorLayout)
        majorLayout.startAnimation(fadeInAnimation)

        val contactName = intent.getStringExtra("contact_name")
        val contactNameTextView: TextView = findViewById(R.id.contactNameTextView)
        contactNameTextView.text = contactName

        val viewChatConversation: ImageView = findViewById(R.id.backButton)
        viewChatConversation.setOnClickListener {
            startActivity(Intent(this, ChatList::class.java))
        }

        val viewChatOptions: LinearLayout = findViewById(R.id.viewChatOptions)
        viewChatOptions.setOnClickListener {
            showChatOptionsDialog()
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


    private fun showChatOptionsDialog() {
        val dialog = Dialog(this)
        dialog.setContentView(R.layout.chat_options_layout)
        val fadeInAnimation = AlphaAnimation(0.0f, 1.0f)
        fadeInAnimation.duration = 1000
        val majorLayout = findViewById<ConstraintLayout>(R.id.majorLayout)
        majorLayout.startAnimation(fadeInAnimation)

        val window = dialog.window
        val layoutParams = window?.attributes
        layoutParams?.apply {
            gravity = Gravity.TOP or Gravity.END
        }

        window?.attributes = layoutParams
        dialog.show()

        dialog.setOnDismissListener{
            startActivity(Intent(this, Chat::class.java))
        }
    }

}