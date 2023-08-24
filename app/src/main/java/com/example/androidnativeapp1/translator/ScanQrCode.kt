package com.example.androidnativeapp1.translator

import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.view.Gravity
import androidx.appcompat.app.AppCompatActivity
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.cardview.widget.CardView
import com.example.androidnativeapp1.ConfirmLogoutLayout

import androidx.core.app.ActivityCompat

import com.example.androidnativeapp1.LeftDrawerLayout
import com.example.androidnativeapp1.R
import com.example.androidnativeapp1.home.Home
import com.example.androidnativeapp1.learn.ListOfLessons
import com.example.androidnativeapp1.learn.OngoingQuiz
import com.example.androidnativeapp1.settings.Profile
import com.example.androidnativeapp1.video_chat.ChatInitialPage
import com.example.androidnativeapp1.video_chat.ChatList
import com.example.androidnativeapp1.video_chat.ChatOptionsLayout
import com.google.android.material.bottomnavigation.BottomNavigationView
import android.graphics.Bitmap
import android.view.animation.AlphaAnimation
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.androidnativeapp1.HelpCenter
import com.example.androidnativeapp1.Notifications
import com.example.androidnativeapp1.login.Login
import com.google.zxing.BarcodeFormat
import com.google.zxing.MultiFormatWriter
import com.google.zxing.WriterException
import com.google.zxing.common.BitMatrix
import com.google.zxing.qrcode.QRCodeWriter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.io.BufferedReader
import java.io.DataInputStream
import java.io.DataOutputStream
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL
import java.nio.charset.StandardCharsets
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody
import android.util.Log
import com.google.gson.Gson
import com.google.gson.annotations.SerializedName
import fuel.Fuel
import fuel.post
import kotlinx.coroutines.runBlocking
import org.json.JSONObject

data class User(
    @SerializedName("userID") val userId: String,
    val name: String,
    val email: String,
    @SerializedName("passwordHash") val passwordHash: String,
    val role: String,
    val status: String,
    @SerializedName("profilePictureUrl") val profilePictureUrl: String,
    @SerializedName("phoneNumber") val phoneNumber: String,
    @SerializedName("dateOfBirth") val dateOfBirth: String
)

data class Session(
    @SerializedName("sessionID") val sessionId: String,
    @SerializedName("userID") val userId: String,
    val user: User,
    @SerializedName("startDate") val startDate: String,
    @SerializedName("endDate") val endDate: String,
    val status: String,
    val translatedText: String
)

data class ApiResponse(
    val code: Int,
    val success: Boolean,
    val message: String,
    val data: List<Session>,
    @SerializedName("errorMessage") val errorMessage: String?
)


class ScanQrCode : AppCompatActivity() {
    private val client = OkHttpClient()
    var httpResponse = ""
    var sessionID = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.scan_qr_code)
        val fadeInAnimation = AlphaAnimation(0.0f, 1.0f)
        fadeInAnimation.duration = 1000
        val majorLayout = findViewById<ConstraintLayout>(R.id.majorLayout)
        majorLayout.startAnimation(fadeInAnimation)

        val userID = "f69deaaf-5c41-4ba2-8bad-e44e026b516b"
        val urlToApi = "https://api-be-my-voice.azurewebsites.net/api/session/create-session"
        val requestBody = "{\"userID\":\"$userID\"}"

        val scanQrCodeDescription: TextView = findViewById(R.id.scanQrCodeDescription)
        try {
            GlobalScope.launch(Dispatchers.IO) {
                httpResponse = apiCallCreateSession(urlToApi, requestBody)
                Log.d("httpResponse", httpResponse)
                val gson = Gson()
                val apiResponse = gson.fromJson(httpResponse, ApiResponse::class.java)
                sessionID = apiResponse.data[0].sessionId
                scanQrCodeDescription.text = sessionID
            }

        } catch (e: Exception) {
            scanQrCodeDescription.text = e.toString()
        }

        //Enter the URL containing session ID returned by response
        val urlForQrLink = "https://normal-user-web.vercel.app"+sessionID
        val qrCodeBitmap = generateQRCode(urlForQrLink)
        val imageView = findViewById<ImageView>(R.id.QRImageView)
        imageView.setImageBitmap(qrCodeBitmap)

        val generateNewQrCode: Button = findViewById(R.id.generateNewQrCode)
        generateNewQrCode.setOnClickListener {
            var intent = Intent(this, SessionStartView::class.java)
            intent.putExtra("sessionID", sessionID)
            startActivity(intent)
        }

        val leftDrawerIcon: ImageView = findViewById(R.id.leftDrawerIcon)
        leftDrawerIcon.setOnClickListener {
            displayLeftDrawer()
        }

        val notificationIcon: ImageView = findViewById(R.id.notificationIcon)
        notificationIcon.setOnClickListener {
            startActivity(Intent(this, Notifications::class.java))
        }

        val bottomNavigationView: BottomNavigationView = findViewById(R.id.bottom_navigation)
        bottomNavigationView.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.home_tab -> {
                    startActivity(Intent(this, Home::class.java))
                    true
                }

                R.id.camera_tab -> {
                    startActivity(Intent(this, ScanQrCode::class.java))
                    true
                }

                R.id.learn_tab -> {
                    startActivity(Intent(this, ListOfLessons::class.java))
                    true
                }

                R.id.chat_tab -> {
                    startActivity(Intent(this, ChatInitialPage::class.java))
                    true
                }

                else -> false
            }
        }
        bottomNavigationView.getMenu().getItem(1).setChecked(true)
    }


    private fun displayLeftDrawer() {
        val dialog = Dialog(this)
        dialog.setContentView(R.layout.left_drawer_layout)
        val fadeInAnimation = AlphaAnimation(0.0f, 1.0f)
        fadeInAnimation.duration = 1000
        val majorLayout = findViewById<ConstraintLayout>(R.id.majorLayout)
        majorLayout.startAnimation(fadeInAnimation)

        val window = dialog.window
        val layoutParams = window?.attributes
        layoutParams?.apply {
            gravity = Gravity.TOP or Gravity.START
        }
        window?.attributes = layoutParams
        dialog.show()


        val closeLeftDrawerButton = dialog.findViewById<TextView>(R.id.closeLeftDrawerButton)
        closeLeftDrawerButton.setOnClickListener {
            dialog.dismiss()
        }

        val settingsButton = dialog.findViewById<LinearLayout>(R.id.settingsButton)
        settingsButton.setOnClickListener {
            dialog.dismiss()
            startActivity(Intent(this, Profile::class.java))
        }
        val helpButton = dialog.findViewById<LinearLayout>(R.id.helpButton)
        helpButton.setOnClickListener {
            dialog.dismiss()
            startActivity(Intent(this, HelpCenter::class.java))
        }
        val logoutButton = dialog.findViewById<LinearLayout>(R.id.logoutButton)
        logoutButton.setOnClickListener {
            dialog.dismiss()
            confirmLogoutDialog()
        }
    }



    private fun generateQRCode(url: String): Bitmap? {
        val qrCodeWriter = QRCodeWriter()
        try {
            val bitMatrix: BitMatrix = qrCodeWriter.encode(url, BarcodeFormat.QR_CODE, 512, 512)
            val width = bitMatrix.width
            val height = bitMatrix.height
            val bmp = Bitmap.createBitmap(width, height, Bitmap.Config.RGB_565)
            for (x in 0 until width) {
                for (y in 0 until height) {
                    bmp.setPixel(
                        x,
                        y,
                        if (bitMatrix.get(
                                x,
                                y
                            )
                        ) resources.getColor(R.color.black) else resources.getColor(R.color.white)
                    )
                }
            }
            return bmp
        } catch (e: WriterException) {
            e.printStackTrace()
            return null
        }
    }

    private fun confirmLogoutDialog() {
        val dialog = Dialog(this)
        dialog.setContentView(R.layout.confirm_logout_layout)
        val fadeInAnimation = AlphaAnimation(0.0f, 1.0f)
        fadeInAnimation.duration = 1000
        val majorLayout = findViewById<ConstraintLayout>(R.id.majorLayout)
        majorLayout.startAnimation(fadeInAnimation)

        val confirmLogoutButton = dialog.findViewById<Button>(R.id.confirmLogoutButton)
        confirmLogoutButton.setOnClickListener {
            dialog.dismiss()
            startActivity(Intent(this, Login::class.java))
        }

        val cancelLogoutButton = dialog.findViewById<Button>(R.id.cancelLogoutButton)
        cancelLogoutButton.setOnClickListener {
            dialog.dismiss()
        }
        dialog.show()
    }

    fun apiCallCreateSession(urlString: String, requestBody: String): String {
        val mediaType = "application/json; charset=utf-8".toMediaType()

        val request = Request.Builder()
            .url(urlString)
            .post(requestBody.toRequestBody(mediaType))
            .build()

        val response = client.newCall(request).execute()
        if (response.isSuccessful) {
            return response.body?.string() ?: ""
        } else {
            throw Exception("HTTP POST request failed with response code: ${response.code}")
        }
    }

    private fun checkPermissions() {
        if (!allPermissionsGranted()) {
            // Request camera-related permissions, media store permissions, and audio permissions
            val REQUIRED_PERMISSIONS = arrayOf(
                android.Manifest.permission.CAMERA,
                android.Manifest.permission.RECORD_AUDIO,
                android.Manifest.permission.WRITE_EXTERNAL_STORAGE,
                android.Manifest.permission.READ_EXTERNAL_STORAGE
            )
            val REQUEST_CODE_PERMISSIONS = 10
            ActivityCompat.requestPermissions(
                this, REQUIRED_PERMISSIONS, REQUEST_CODE_PERMISSIONS
            )
        } else {
            return;
        }

    }

    private fun allPermissionsGranted(): Boolean {
        if (ActivityCompat.checkSelfPermission(
                this,
                android.Manifest.permission.CAMERA
            ) == android.content.pm.PackageManager.PERMISSION_GRANTED &&
            ActivityCompat.checkSelfPermission(
                this,
                android.Manifest.permission.RECORD_AUDIO
            ) == android.content.pm.PackageManager.PERMISSION_GRANTED &&
            ActivityCompat.checkSelfPermission(
                this,
                android.Manifest.permission.WRITE_EXTERNAL_STORAGE
            ) == android.content.pm.PackageManager.PERMISSION_GRANTED &&
            ActivityCompat.checkSelfPermission(
                this,
                android.Manifest.permission.READ_EXTERNAL_STORAGE
            ) == android.content.pm.PackageManager.PERMISSION_GRANTED
        ) {
            return true
        }
        return false
    }

}
