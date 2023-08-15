package com.example.androidnativeapp1.login

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.animation.AlphaAnimation
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import android.widget.Button
import android.widget.EditText
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.androidnativeapp1.R
import com.example.androidnativeapp1.home.Home
import com.example.androidnativeapp1.reset_password.ResetPasswordEmail
import com.example.androidnativeapp1.signup.Signup
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
import android.widget.Toast
import android.content.Context
import com.example.androidnativeapp1.translator.SessionStartView
import kotlinx.coroutines.CoroutineScope

class Login : AppCompatActivity() {
    var httpResponse = ""
    private val client = OkHttpClient()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login)
        val fadeInAnimation = AlphaAnimation(0.0f, 1.0f)
        fadeInAnimation.duration = 1000
        val majorLayout = findViewById<ConstraintLayout>(R.id.majorLayout)
        majorLayout.startAnimation(fadeInAnimation)

        val backToOnboarding4: TextView = findViewById(R.id.backToOnboarding4)
        backToOnboarding4.setOnClickListener {
            startActivity(Intent(this, Onboarding4::class.java))
        }

        val resetForgotPassword: TextView = findViewById(R.id.resetForgotPassword)
        resetForgotPassword.setOnClickListener {
            startActivity(Intent(this, ResetPasswordEmail::class.java))
        }

        val loginButton: Button = findViewById(R.id.loginButton)
        loginButton.setOnClickListener {
            val email: EditText = findViewById(R.id.email)
            val password: EditText = findViewById(R.id.password)
            val errorText: TextView = findViewById(R.id.errorText)
            val regexEmail = Regex("[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,4}")
            val context: Context = applicationContext
            val messageInvalidCredentials = "Sorry! Credentials are incorrect."
            val messageServerError = "Sorry! Something is wrong. Please try again later."
            val duration = Toast.LENGTH_SHORT
            val toastInvalidCredentials = Toast.makeText(context, messageInvalidCredentials, duration)
            val toastServerError = Toast.makeText(context, messageServerError, duration)
            if(regexEmail.matches(email.text) == false){
                errorText.text = "Please enter valid email !"
            }else if((password.text).length <= 8){
                errorText.text = "Please enter valid password !"
            }else{
                val urlToApi = "https://api-be-my-voice.azurewebsites.net/api/user/login"
                val emailText = email.text
                val passwordText = password.text
                val requestBody = "{\"email\":\"$emailText\" , \"password\":\"$passwordText\"}"
                GlobalScope.launch(Dispatchers.IO) {
                    httpResponse = apiCallLogin(urlToApi, requestBody)
                    val gson = Gson()
                    val apiResponse = gson.fromJson(httpResponse, ApiResponse::class.java)
                    val loginMessage = apiResponse.message
                    when (loginMessage) {
                        "User logged in" ->
                            {   val userID = apiResponse.data[0].user.userId
                                val username = apiResponse.data[0].user.name
                                Log.d("userID", userID)
                                var intent = Intent(this@Login, Home::class.java)
                                intent.putExtra("userID", userID)
                                intent.putExtra("username", username)
                                startActivity(intent)
                                //startActivity(Intent(this@Login, Home::class.java))
                            }
                        "Invalid Credentials" -> toastInvalidCredentials.show()
                        else -> toastServerError.show()
                    }
                    /*if(loginMessage == "User logged in"){
                        startActivity(Intent(this@Login, Home::class.java))
                    }else if(loginMessage == "Invalid Credentials"){
                        errorText.text = "Sorry! Credentials are invalid."
                    }else{
                        errorText.text = "Sorry! Something went wrong. Please try again later."
                    } */
                }
            }
            //startActivity(Intent(this, Home::class.java))
        }

        val linkToSignUp: TextView = findViewById(R.id.linkToSignUp)
        linkToSignUp.setOnClickListener {
            startActivity(Intent(this, Signup::class.java))
        }
    }

    fun apiCallLogin(urlString: String, requestBody: String): String {
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
}