package com.example.androidnativeapp1.video_chat

import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.view.Gravity
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import android.widget.Button
import androidx.cardview.widget.CardView
import com.example.androidnativeapp1.R
import com.example.androidnativeapp1.home.SavedTranslations
import com.example.androidnativeapp1.login.Login


class ChatOptionsLayout : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        showChatOptionsDialog()

    }

    private fun showChatOptionsDialog() {
        val dialog = Dialog(this)
        dialog.setContentView(R.layout.chat_options_layout)

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