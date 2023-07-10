package com.example.androidnativeapp1.reset_password

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import android.widget.Button
import com.example.androidnativeapp1.R


class CreateNewPassword: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.create_new_password)

        val backButton: TextView = findViewById(R.id.backButton)
        backButton.setOnClickListener {
            finish()
        }

        val createNewPasswordButton: Button = findViewById(R.id.createNewPasswordButton)
        createNewPasswordButton.setOnClickListener {
            startActivity(Intent(this, ResetPasswordLayout::class.java))
        }

    }

}