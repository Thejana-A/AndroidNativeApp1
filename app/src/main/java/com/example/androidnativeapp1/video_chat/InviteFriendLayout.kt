package com.example.androidnativeapp1.video_chat

import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.view.Gravity
import android.view.animation.AlphaAnimation
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import android.widget.Button
import androidx.cardview.widget.CardView
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.androidnativeapp1.R
import com.example.androidnativeapp1.home.Home
import com.example.androidnativeapp1.home.SavedTranslations
import com.example.androidnativeapp1.learn.ListOfQuizzes
import com.example.androidnativeapp1.login.Login


class InviteFriendLayout : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        inviteFriendDialog()

    }

    private fun inviteFriendDialog() {
        val dialog = Dialog(this)
        dialog.setContentView(R.layout.invite_friend_layout)
        val fadeInAnimation = AlphaAnimation(0.0f, 1.0f)
        fadeInAnimation.duration = 1000
        val majorLayout = findViewById<ConstraintLayout>(R.id.majorLayout)
        majorLayout.startAnimation(fadeInAnimation)

        val inviteButton = dialog.findViewById<Button>(R.id.inviteButton)
        inviteButton.setOnClickListener {
            dialog.dismiss()
            startActivity(Intent(this, ContactList::class.java))
        }

        val cancelButton = dialog.findViewById<Button>(R.id.cancelButton)
        cancelButton.setOnClickListener {
            dialog.dismiss()
            startActivity(Intent(this, ContactList::class.java))
        }
        dialog.show()
    }

}