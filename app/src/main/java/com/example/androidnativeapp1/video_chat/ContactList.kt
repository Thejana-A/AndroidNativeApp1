package com.example.androidnativeapp1.video_chat

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.view.Gravity
import android.view.animation.AlphaAnimation
import androidx.appcompat.app.AppCompatActivity
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.androidnativeapp1.ConfirmLogoutLayout
import com.example.androidnativeapp1.HelpCenter
import com.example.androidnativeapp1.LeftDrawerLayout
import com.example.androidnativeapp1.Notifications
import com.example.androidnativeapp1.R
import com.example.androidnativeapp1.home.Home
import com.example.androidnativeapp1.learn.ListOfLessons
import com.example.androidnativeapp1.login.Login
import com.example.androidnativeapp1.settings.Profile
import com.example.androidnativeapp1.translator.ScanQrCode
import com.google.android.material.bottomnavigation.BottomNavigationView
import android.content.ContentResolver
import android.content.Context
import android.provider.ContactsContract
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

data class Contact(val name: String, val phoneNumber: String)

class ContactList : AppCompatActivity() {

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.contact_list)
        val fadeInAnimation = AlphaAnimation(0.0f, 1.0f)
        fadeInAnimation.duration = 1000
        val majorLayout = findViewById<ConstraintLayout>(R.id.majorLayout)
        majorLayout.startAnimation(fadeInAnimation)

        var inviteStatus = intent.getStringExtra("invite_status")
        if(inviteStatus == "true"){
            inviteStatus = ""
            inviteFriendDialog()
        }

        val contactList = getContactList(this)
        var contactListString = ""

        val recyclerview = findViewById<RecyclerView>(R.id.recyclerview)
        recyclerview.layoutManager = LinearLayoutManager(this)

        // ArrayList of class ItemsViewModel
        val data = ArrayList<ContactViewModel>()
        for (contact in contactList) {
            data.add(ContactViewModel(R.drawable.profile_picture, "${contact.name}", "${contact.phoneNumber}"))
            //contactListString += "Name: ${contact.name}" + " " + "Phone: ${contact.phoneNumber}"
        }

        val adapter = ContactAdapter(data)
        recyclerview.adapter = adapter


        /*val viewChatConversation: CardView = findViewById(R.id.viewChatConversation)
        viewChatConversation.setOnClickListener {
            startActivity(Intent(this, Chat::class.java))
        }

        val inviteFriendButton: Button = findViewById(R.id.inviteFriendButton)
        inviteFriendButton.setOnClickListener {
            inviteFriendDialog()
        } */

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
        bottomNavigationView.getMenu().getItem(3).setChecked(true)

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

    fun inviteFriendDialog() {
        val dialog = Dialog(this)
        dialog.setContentView(R.layout.invite_friend_layout)
        val fadeInAnimation = AlphaAnimation(0.0f, 1.0f)
        fadeInAnimation.duration = 1000
        val majorLayout = findViewById<ConstraintLayout>(R.id.majorLayout)
        majorLayout.startAnimation(fadeInAnimation)

        val inviteButton = dialog.findViewById<Button>(R.id.inviteButton)
        inviteButton.setOnClickListener {
            dialog.dismiss()
        }

        val cancelButton = dialog.findViewById<Button>(R.id.cancelButton)
        cancelButton.setOnClickListener {
            dialog.dismiss()
        }
        dialog.show()
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

    @SuppressLint("Range")
    fun getContactList(context: Context): List<Contact> {
        val contactsList = mutableListOf<Contact>()
        val contentResolver: ContentResolver = context.contentResolver

        // Define the columns you want to retrieve
        val projection = arrayOf(
            ContactsContract.Contacts.DISPLAY_NAME,
            ContactsContract.CommonDataKinds.Phone.NUMBER
        )

        // Perform the query on the contacts table
        val cursor = contentResolver.query(
            ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
            projection,
            null,
            null,
            null
        )

        cursor?.use {
            while (it.moveToNext()) {
                val name =
                    it.getString(it.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME))
                val phoneNumber =
                    it.getString(it.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER))

                contactsList.add(Contact(name, phoneNumber))
            }
        }

        cursor?.close()
        return contactsList
    }

}