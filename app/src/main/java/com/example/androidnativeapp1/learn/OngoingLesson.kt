package com.example.androidnativeapp1.learn

import android.app.Dialog
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.Gravity
import android.view.animation.AlphaAnimation
import androidx.appcompat.app.AppCompatActivity
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.MediaController
import android.widget.TextView
import android.widget.VideoView
import androidx.cardview.widget.CardView
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.androidnativeapp1.ConfirmLogoutLayout
import com.example.androidnativeapp1.HelpCenter
import com.example.androidnativeapp1.LeftDrawerLayout
import com.example.androidnativeapp1.Notifications
import com.example.androidnativeapp1.R
import com.example.androidnativeapp1.home.Home
import com.example.androidnativeapp1.login.Login
import com.example.androidnativeapp1.settings.Profile
import com.example.androidnativeapp1.translator.ScanQrCode
import com.example.androidnativeapp1.video_chat.ChatInitialPage
import com.google.android.material.bottomnavigation.BottomNavigationView


class OngoingLesson : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.ongoing_lesson)
        val fadeInAnimation = AlphaAnimation(0.0f, 1.0f)
        fadeInAnimation.duration = 1000
        val majorLayout = findViewById<ConstraintLayout>(R.id.majorLayout)
        majorLayout.startAnimation(fadeInAnimation)

        var lessonHeading = intent.getStringExtra("lesson_name")
        var lessonName = intent.getStringExtra("sub_lesson_name")
        var lessonHeadingTextView = findViewById<TextView>(R.id.lessonHeading)
        var lessonTitle = findViewById<TextView>(R.id.lessonTitle)
        lessonTitle.text = lessonName
        lessonHeadingTextView.text = lessonHeading

        val videoView = findViewById<VideoView>(R.id.videoView)
        val videoPath = intent.getStringExtra("sub_lesson_video")
        val mediaController = MediaController(this)
        mediaController.setAnchorView(videoView)
        videoView.setMediaController(mediaController)
        videoView.setVideoURI(Uri.parse(videoPath))
        videoView.start()


        val backButton: TextView = findViewById(R.id.backButton)
        backButton.setOnClickListener {
            finish()
        }

        val leftDrawerIcon: ImageView = findViewById(R.id.leftDrawerIcon)
        leftDrawerIcon.setOnClickListener {
            displayLeftDrawer()
        }

        val notificationIcon: ImageView = findViewById(R.id.notificationIcon)
        notificationIcon.setOnClickListener {
            startActivity(Intent(this, Notifications::class.java))
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
        bottomNavigationView.getMenu().getItem(2).setChecked(true)

    }


    private fun displayLeftDrawer() {
        val dialog = Dialog(this)
        dialog.setContentView(R.layout.left_drawer_layout)
        val fadeInAnimation = AlphaAnimation(0.0f, 1.0f)
        fadeInAnimation.duration = 1000
        val majorLayout = findViewById<ConstraintLayout>(R.id.majorLayout)
        majorLayout.startAnimation(fadeInAnimation)

        val window = dialog.window
        val layoutParams = window?.attributes
        layoutParams?.apply {
            gravity = Gravity.TOP or Gravity.START
        }
        window?.attributes = layoutParams
        dialog.show()


        val closeLeftDrawerButton = dialog.findViewById<TextView>(R.id.closeLeftDrawerButton)
        closeLeftDrawerButton.setOnClickListener {
            dialog.dismiss()
        }

        val settingsButton = dialog.findViewById<LinearLayout>(R.id.settingsButton)
        settingsButton.setOnClickListener {
            dialog.dismiss()
            startActivity(Intent(this, Profile::class.java))
        }
        val helpButton = dialog.findViewById<LinearLayout>(R.id.helpButton)
        helpButton.setOnClickListener {
            dialog.dismiss()
            startActivity(Intent(this, HelpCenter::class.java))
        }
        val logoutButton = dialog.findViewById<LinearLayout>(R.id.logoutButton)
        logoutButton.setOnClickListener {
            dialog.dismiss()
            confirmLogoutDialog()
        }
    }

    private fun confirmLogoutDialog() {
        val dialog = Dialog(this)
        dialog.setContentView(R.layout.confirm_logout_layout)
        val fadeInAnimation = AlphaAnimation(0.0f, 1.0f)
        fadeInAnimation.duration = 1000
        val majorLayout = findViewById<ConstraintLayout>(R.id.majorLayout)
        majorLayout.startAnimation(fadeInAnimation)

        val confirmLogoutButton = dialog.findViewById<Button>(R.id.confirmLogoutButton)
        confirmLogoutButton.setOnClickListener {
            dialog.dismiss()
            startActivity(Intent(this, Login::class.java))
        }

        val cancelLogoutButton = dialog.findViewById<Button>(R.id.cancelLogoutButton)
        cancelLogoutButton.setOnClickListener {
            dialog.dismiss()
        }
        dialog.show()
    }
}