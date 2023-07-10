package com.example.androidnativeapp1.signup

import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import android.widget.Button
import android.widget.DatePicker
import com.example.androidnativeapp1.R
import com.example.androidnativeapp1.splash_screen.Onboarding4
import java.util.Calendar


class Signup : AppCompatActivity() {

    private lateinit var selectedDateTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.signup)

        val backToOnboarding4: TextView = findViewById(R.id.backToOnboarding4)
        backToOnboarding4.setOnClickListener {
            startActivity(Intent(this, Onboarding4::class.java))
        }

        val continueSignup: Button = findViewById(R.id.continueSignup)
        continueSignup.setOnClickListener {
            startActivity(Intent(this, SignupFinish::class.java))
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