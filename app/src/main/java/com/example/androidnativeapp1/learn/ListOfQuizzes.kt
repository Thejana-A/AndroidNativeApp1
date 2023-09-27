package com.example.androidnativeapp1.learn

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.view.Gravity
import android.view.animation.AlphaAnimation
import androidx.appcompat.app.AppCompatActivity
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.androidnativeapp1.HelpCenter
import com.example.androidnativeapp1.Notifications
import com.example.androidnativeapp1.R
import com.example.androidnativeapp1.home.Home
import com.example.androidnativeapp1.login.Login
import com.example.androidnativeapp1.settings.Profile
import com.example.androidnativeapp1.translator.ScanQrCode
import com.example.androidnativeapp1.video_chat.ChatInitialPage
import com.google.android.material.bottomnavigation.BottomNavigationView
import org.json.JSONObject


class ListOfQuizzes : AppCompatActivity() {

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.list_of_quizzes)
        val fadeInAnimation = AlphaAnimation(0.0f, 1.0f)
        fadeInAnimation.duration = 1000
        val majorLayout = findViewById<ConstraintLayout>(R.id.majorLayout)
        majorLayout.startAnimation(fadeInAnimation)

        /*val linkToStartQuiz: Button = findViewById(R.id.linkToStartQuiz)
        linkToStartQuiz.setOnClickListener {
            startActivity(Intent(this, OngoingQuiz::class.java))
        } */

        val quizList = "{data : [{\"quiz_name\":\"Days of week\", \"completed_status\":\"Not completed\", \"number_of_questions\":\"7\"}, {\"quiz_name\":\"Verbs\", \"completed_status\":\"completed\", \"number_of_questions\":\"15\"}, {\"quiz_name\":\"Animals\", \"completed_status\":\"completed\", \"number_of_questions\":\"15\"}] }"
        var quizListString = ""

        val recyclerview = findViewById<RecyclerView>(R.id.quizRecyclerView)
        recyclerview.layoutManager = LinearLayoutManager(this)

        // ArrayList of class ItemsViewModel
        val quizData = ArrayList<QuizViewModel>()
        var buttonState = ""

        try {
            val jsonObject = JSONObject(quizList)
            val quizArray = jsonObject.getJSONArray("data")

            for (i in 0 until quizArray.length()) {
                val quizObject = quizArray.getJSONObject(i)
                val quizName = quizObject.getString("quiz_name")
                val completedStatus = quizObject.getString("completed_status")
                if(completedStatus == "Not completed"){
                    buttonState = "Start"
                }else{
                    buttonState = "Completed"
                }
                quizData.add(QuizViewModel("$quizName", "$buttonState"))
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }

        val adapter = QuizAdapter(quizData)
        recyclerview.adapter = adapter

        val lessonsButton: Button = findViewById(R.id.lessonsButton)
        lessonsButton.setOnClickListener {
            startActivity(Intent(this, ListOfLessons::class.java))
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
        bottomNavigationView.getMenu().getItem(2).setChecked(true)

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
}