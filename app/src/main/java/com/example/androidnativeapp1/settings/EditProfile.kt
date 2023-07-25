package com.example.androidnativeapp1.settings

import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.animation.AlphaAnimation
import androidx.appcompat.app.AppCompatActivity
import android.widget.Button
import android.widget.DatePicker
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.androidnativeapp1.R
import java.util.Calendar


class EditProfile : AppCompatActivity() {

    private lateinit var selectedDateTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.edit_profile)
        val fadeInAnimation = AlphaAnimation(0.0f, 1.0f)
        fadeInAnimation.duration = 1000
        val majorLayout = findViewById<ConstraintLayout>(R.id.majorLayout)
        majorLayout.startAnimation(fadeInAnimation)

        val backButton: TextView = findViewById(R.id.backButton)
        backButton.setOnClickListener {
            startActivity(Intent(this, Profile::class.java))
        }

    }

    fun showDatePicker(view: View) {
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH)

        val datePickerDialog = DatePickerDialog(
            this,
            DatePickerDialog.OnDateSetListener { _: DatePicker?, year: Int, month: Int, dayOfMonth: Int ->
                // Do something with the selected date
                val selectedDate = "$dayOfMonth/${month + 1}/$year"
                selectedDateTextView.text = selectedDate
            },
            year,
            month,
            dayOfMonth
        )

        datePickerDialog.show()
    }

}