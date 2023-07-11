package com.example.androidnativeapp1.settings

import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import android.widget.Button
import android.widget.DatePicker
import android.widget.TextView
import com.example.androidnativeapp1.R
import java.util.Calendar


class FrequentlyAskedQuestions : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.frequently_asked_questions)

        val backButton: TextView = findViewById(R.id.backButton)
        backButton.setOnClickListener {
            startActivity(Intent(this, Profile::class.java))
        }

    }


}