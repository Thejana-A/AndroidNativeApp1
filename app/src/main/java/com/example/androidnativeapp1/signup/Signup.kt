package com.example.androidnativeapp1.signup

import android.app.DatePickerDialog
import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.animation.AlphaAnimation
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import android.widget.Button
import android.widget.CheckBox
import android.widget.DatePicker
import android.widget.EditText
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.androidnativeapp1.R
import com.example.androidnativeapp1.home.Home
import com.example.androidnativeapp1.login.Login
import com.example.androidnativeapp1.splash_screen.Onboarding4
import com.example.androidnativeapp1.translator.ApiResponse
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody
import java.util.Calendar


class Signup : AppCompatActivity() {

    private lateinit var selectedDateTextView: TextView
    private val client = OkHttpClient()
    var httpResponse = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.signup)
        val fadeInAnimation = AlphaAnimation(0.0f, 1.0f)
        fadeInAnimation.duration = 1000
        val majorLayout = findViewById<ConstraintLayout>(R.id.majorLayout)
        majorLayout.startAnimation(fadeInAnimation)

        if(intent.getStringExtra("signupMessage") == "User created"){
            signupFinishDialog()
        }

        val backToOnboarding4: TextView = findViewById(R.id.backToOnboarding4)
        backToOnboarding4.setOnClickListener {
            startActivity(Intent(this, Onboarding4::class.java))
        }

        //val continueSignup: Button = findViewById(R.id.continueSignup)
        val signupButton: Button = findViewById(R.id.signupButton)
        signupButton.setOnClickListener {
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
            val checkBox = findViewById<CheckBox>(R.id.myCheckbox)
            var ckeckboxState = "";
            if (checkBox.isChecked) {
                ckeckboxState = "checked"
            }
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
            }else if(ckeckboxState.equals("")){
                errorText.text = "Please agree to the terms and conditions !"
            }else{
                val urlToApi = "https://api-be-my-voice.azurewebsites.net/api/user/register"
                val nameText = name.text.toString()
                val emailText = email.text.toString()
                val phoneNumberText = contactNo.text.toString()
                val dateOfBirthText = datePickerButton.text.toString()
                val dobParts = dateOfBirthText.split("/")
                val formattedDOB = dobParts[2] + "-" + dobParts[1].padStart(2, '0') + "-" + dobParts[0].padStart(2, '0')
                val requestBody = "{\"name\":\"$nameText\" , \"email\":\"$emailText\" , \"role\":\"mute_user\" , \"status\":\"free\" , \"password\":\"$passwordText\", \"profilePictureUrl\":\"\" , \"phoneNumber\":\"$phoneNumberText\" , \"dateOfBirth\":\"$formattedDOB\" }"
                val context: Context = applicationContext
                val messageUserExists = "Sorry! User already exists."
                val messageServerError = "Sorry! Something is wrong. Please try again later."
                val duration = Toast.LENGTH_SHORT
                val toastUserExists = Toast.makeText(context, messageUserExists, duration)
                val toastServerError = Toast.makeText(context, messageServerError, duration)

                GlobalScope.launch(Dispatchers.IO) {
                    httpResponse = apiCallSignup(urlToApi, requestBody)
                    val gson = Gson()
                    val apiResponse = gson.fromJson(httpResponse, ApiResponse::class.java)
                    val signupMessage = apiResponse.message
                    Log.d("signupMessage", signupMessage)
                    when (signupMessage) {
                        "User created" ->
                            {
                                var intent = Intent(this@Signup, Signup::class.java)
                                intent.putExtra("signupMessage", signupMessage)
                                startActivity(intent)
                            }
                        "User already exists" -> toastUserExists.show()
                        else -> toastServerError.show()
                    }
                }
            }
            //startActivity(Intent(this, SignupFinish::class.java))
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

    fun apiCallSignup(urlString: String, requestBody: String): String {
        val mediaType = "application/json; charset=utf-8".toMediaType()

        val request = Request.Builder()
            .url(urlString)
            .post(requestBody.toRequestBody(mediaType))
            .build()

        val response = client.newCall(request).execute()
        if (response.isSuccessful) {
            return response.body.string() ?: ""
        } else {
            throw Exception("HTTP POST request failed with response code: ${response.code}")
        }
    }

    private fun signupFinishDialog() {
        val dialog = Dialog(this)
        dialog.setContentView(R.layout.signup_finish_layout)
        val fadeInAnimation = AlphaAnimation(0.0f, 1.0f)
        fadeInAnimation.duration = 1000
        val majorLayout = findViewById<ConstraintLayout>(R.id.majorLayout)
        majorLayout.startAnimation(fadeInAnimation)

        val signupFinishLoginButton = dialog.findViewById<Button>(R.id.signupFinishLoginButton)
        signupFinishLoginButton.setOnClickListener {
            dialog.dismiss()
            startActivity(Intent(this, Login::class.java))
        }
        dialog.show()
    }

}