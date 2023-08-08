package com.example.androidnativeapp1.signup

import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.animation.AlphaAnimation
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import android.widget.Button
import android.widget.DatePicker
import android.widget.EditText
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.androidnativeapp1.R
import com.example.androidnativeapp1.splash_screen.Onboarding4
import java.util.Calendar


class Signup : AppCompatActivity() {

    private lateinit var selectedDateTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.signup)
        val fadeInAnimation = AlphaAnimation(0.0f, 1.0f)
        fadeInAnimation.duration = 1000
        val majorLayout = findViewById<ConstraintLayout>(R.id.majorLayout)
        majorLayout.startAnimation(fadeInAnimation)

        val backToOnboarding4: TextView = findViewById(R.id.backToOnboarding4)
        backToOnboarding4.setOnClickListener {
            startActivity(Intent(this, Onboarding4::class.java))
        }

        val continueSignup: Button = findViewById(R.id.continueSignup)
        continueSignup.setOnClickListener {
            val name: EditText = findViewById(R.id.name)
            val contactNo: EditText = findViewById(R.id.contactNo)
            val email: EditText = findViewById(R.id.email)
            val datePickerButton: Button = findViewById(R.id.datePickerButton)
            val password: EditText = findViewById(R.id.password)
            val confirmPassword: EditText = findViewById(R.id.confirmPassword)
            val errorText: TextView = findViewById(R.id.errorText)
            val regexEmail = Regex("[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,4}")
            val regexContactNo = Regex("^\\+\\d{2} \\d{3} \\d{3} \\d{3}\$")
            val passwordText = password.text.toString()
            val confirmPasswordText = confirmPassword.text.toString()
            if(name.text?.isEmpty() == true) {
                errorText.text = "Please enter your name !"
            }else if(contactNo.text?.isEmpty() == true){
                errorText.text = "Please enter your contact number !"
            }else if(datePickerButton.text?.equals("Enter your Date of Birth") == true){
                errorText.text = "Please enter your date of birth !"
            }else if(email.text?.isEmpty() == true){
                errorText.text = "Please enter your email !"
            }else if(password.text?.isEmpty() == true){
                errorText.text = "Please enter your password !"
            }else if(confirmPassword.text?.isEmpty() == true){
                errorText.text = "Please enter your confirm password !"
            }else if(regexEmail.matches(email.text) == false){
                errorText.text = "Please enter valid email !"
            }else if(regexContactNo.matches(contactNo.text) == false){
                errorText.text = "Please enter contact number in +94 123 456 789 format !"
            }else if((password.text).length <= 8){
                errorText.text = "Password should have more than 8 characters !"
            }else if(!(passwordText.equals(confirmPasswordText))){
                errorText.text = "Confirm password correctly !"
            }else{
                startActivity(Intent(this, SignupFinish::class.java))
            }
        }
    }

    fun showDatePicker(view: View) {
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH)
        val datePickerButton: Button = findViewById(R.id.datePickerButton)
        val datePickerDialog = DatePickerDialog(
            this,
            DatePickerDialog.OnDateSetListener { _: DatePicker?, year: Int, month: Int, dayOfMonth: Int ->
                // Do something with the selected date
                val selectedDate = "$dayOfMonth/${month + 1}/$year"
                datePickerButton.text = selectedDate
            },
            year,
            month,
            dayOfMonth
        )

        datePickerDialog.show()
    }


}