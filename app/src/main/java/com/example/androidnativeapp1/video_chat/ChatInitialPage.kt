package com.example.androidnativeapp1.video_chat

import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.view.Gravity
import androidx.appcompat.app.AppCompatActivity
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.cardview.widget.CardView
import com.example.androidnativeapp1.ConfirmLogoutLayout
import com.example.androidnativeapp1.LeftDrawerLayout
import com.example.androidnativeapp1.R
import com.example.androidnativeapp1.home.Home
import com.example.androidnativeapp1.learn.ListOfLessons
import com.example.androidnativeapp1.learn.OngoingQuiz
import com.example.androidnativeapp1.login.Login
import com.example.androidnativeapp1.settings.Profile
import com.example.androidnativeapp1.translator.ScanQrCode
import com.google.android.material.bottomnavigation.BottomNavigationView


class ChatInitialPage : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.chat_initial_page)

        val chatList: TextView = findViewById(R.id.chatList)
        chatList.setOnClickListener {
            startActivity(Intent(this, ChatList::class.java))
        }

        val contactList: ImageView = findViewById(R.id.contactList)
        contactList.setOnClickListener {
            startActivity(Intent(this, ContactList::class.java))
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
        bottomNavigationView.getMenu().getItem(3).setChecked(true)

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