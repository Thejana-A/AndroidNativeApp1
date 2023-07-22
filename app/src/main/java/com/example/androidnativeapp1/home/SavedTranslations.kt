package com.example.androidnativeapp1.home

import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.view.Gravity
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.cardview.widget.CardView
import com.example.androidnativeapp1.ConfirmLogoutLayout
import com.example.androidnativeapp1.LeftDrawerLayout
import com.example.androidnativeapp1.R
import com.example.androidnativeapp1.databinding.ViewTranslationLayoutBinding
import com.example.androidnativeapp1.learn.ListOfLessons
import com.example.androidnativeapp1.login.Login
import com.example.androidnativeapp1.settings.Profile
import com.example.androidnativeapp1.translator.ScanQrCode
import com.example.androidnativeapp1.video_chat.ChatInitialPage
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
            viewSavedTranslationDialog()
        }

        val leftDrawerIcon: ImageView = findViewById(R.id.leftDrawerIcon)
        leftDrawerIcon.setOnClickListener {
            displayLeftDrawer()
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

    }

    private fun displayLeftDrawer() {
        val dialog = Dialog(this)
        dialog.setContentView(R.layout.left_drawer_layout)

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
        val logoutButton = dialog.findViewById<LinearLayout>(R.id.logoutButton)
        logoutButton.setOnClickListener {
            dialog.dismiss()
            confirmLogoutDialog()
        }

    }


    private fun viewSavedTranslationDialog() {
        val dialog = Dialog(this)
        dialog.setContentView(R.layout.view_translation_layout)

        val closeSavedTranslationButton = dialog.findViewById<TextView>(R.id.closeSavedTranslationButton)
        closeSavedTranslationButton.setOnClickListener {
            dialog.dismiss()
        }
        dialog.show()
    }

    private fun confirmLogoutDialog() {
        val dialog = Dialog(this)
        dialog.setContentView(R.layout.confirm_logout_layout)

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