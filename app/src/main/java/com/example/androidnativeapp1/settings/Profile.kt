package com.example.androidnativeapp1.settings

import android.content.Intent
import android.os.Bundle
import android.view.animation.AlphaAnimation
import androidx.appcompat.app.AppCompatActivity
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.androidnativeapp1.LeftDrawerLayout
import com.example.androidnativeapp1.R
import com.example.androidnativeapp1.home.Home
import com.example.androidnativeapp1.home.SavedTranslations
import com.example.androidnativeapp1.learn.ListOfLessons
import com.example.androidnativeapp1.translator.ScanQrCode
import com.example.androidnativeapp1.video_chat.ChatInitialPage
import com.google.android.material.bottomnavigation.BottomNavigationView


class Profile : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.profile)
        val fadeInAnimation = AlphaAnimation(0.0f, 1.0f)
        fadeInAnimation.duration = 1000
        val majorLayout = findViewById<ConstraintLayout>(R.id.majorLayout)
        majorLayout.startAnimation(fadeInAnimation)

        val backButton: TextView = findViewById(R.id.backButton)
        backButton.setOnClickListener {
            startActivity(Intent(this, Home::class.java))
        }

        val editProfileButton: LinearLayout = findViewById(R.id.editProfile)
        editProfileButton.setOnClickListener {
            startActivity(Intent(this, EditProfile::class.java))
        }

        val notificationsButton: LinearLayout = findViewById(R.id.notifications)
        notificationsButton.setOnClickListener {
            startActivity(Intent(this, NotificationSettings::class.java))
        }

        val privacyPolicyButton: LinearLayout = findViewById(R.id.privacyPolicy)
        privacyPolicyButton.setOnClickListener {
            startActivity(Intent(this, PrivacyPolicy::class.java))
        }

        val securityButton: LinearLayout = findViewById(R.id.security)
        securityButton.setOnClickListener {
            startActivity(Intent(this, Security::class.java))
        }

        val frequentlyAskedQuestionsButton: LinearLayout = findViewById(R.id.frequentlyAskedQuestions)
        frequentlyAskedQuestionsButton.setOnClickListener {
            startActivity(Intent(this, FrequentlyAskedQuestions::class.java))
        }

    }


}