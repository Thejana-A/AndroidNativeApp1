package com.example.androidnativeapp1.reset_password

import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import android.widget.Button
import com.example.androidnativeapp1.R
import com.example.androidnativeapp1.login.Login


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
            resetPasswordFinishDialog()
        }
    }


    private fun resetPasswordFinishDialog() {
        val dialog = Dialog(this)
        dialog.setContentView(R.layout.reset_password_layout)

        val resetPasswordLoginButton = dialog.findViewById<Button>(R.id.resetPasswordLoginButton)
        resetPasswordLoginButton.setOnClickListener {
            dialog.dismiss()
            startActivity(Intent(this, Login::class.java))
        }

        dialog.show()
    }

}