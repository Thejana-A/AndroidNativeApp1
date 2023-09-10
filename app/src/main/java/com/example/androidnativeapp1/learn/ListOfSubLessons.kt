package com.example.androidnativeapp1.learn

import android.app.Dialog
import android.content.Intent
import android.media.MediaMetadataRetriever
import android.net.Uri
import android.os.Bundle
import android.view.Gravity
import android.view.animation.AlphaAnimation
import androidx.appcompat.app.AppCompatActivity
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.MediaController
import android.widget.TextView
import android.widget.VideoView
import androidx.cardview.widget.CardView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.util.TimeUtils.formatDuration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.androidnativeapp1.ConfirmLogoutLayout
import com.example.androidnativeapp1.HelpCenter
import com.example.androidnativeapp1.LeftDrawerLayout
import com.example.androidnativeapp1.Notifications
import com.example.androidnativeapp1.R
import com.example.androidnativeapp1.home.Home
import com.example.androidnativeapp1.login.Login
import com.example.androidnativeapp1.settings.Profile
import com.example.androidnativeapp1.translator.ScanQrCode
import com.example.androidnativeapp1.video_chat.ChatInitialPage
import com.google.android.material.bottomnavigation.BottomNavigationView
import okhttp3.internal.concurrent.formatDuration
import org.json.JSONObject


class ListOfSubLessons : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.list_of_sub_lessons)
        val fadeInAnimation = AlphaAnimation(0.0f, 1.0f)
        fadeInAnimation.duration = 1000
        val majorLayout = findViewById<ConstraintLayout>(R.id.majorLayout)
        majorLayout.startAnimation(fadeInAnimation)

        var lessonName = intent.getStringExtra("lesson_name")
        var lessonTitle = findViewById<TextView>(R.id.lessonTitle)
        lessonTitle.text = "$lessonName"

        val selectSubLesson: CardView = findViewById(R.id.selectSubLesson)
        selectSubLesson.setOnClickListener {
            startActivity(Intent(this, OngoingLesson::class.java))
        }

        /*val videoView1 = findViewById<VideoView>(R.id.videoView1)
        val videoPath1 = "android.resource://" + packageName + "/" + R.raw.sign_lang_video
        val mediaController1 = MediaController(this)
        mediaController1.setAnchorView(videoView1)
        videoView1.setMediaController(mediaController1)
        videoView1.setVideoURI(Uri.parse(videoPath1))
        videoView1.start()
        val videoView2 = findViewById<VideoView>(R.id.videoView2)
        val videoPath2 = "android.resource://" + packageName + "/" + R.raw.sign_lang_video2
        val mediaController2 = MediaController(this)
        mediaController2.setAnchorView(videoView2)
        videoView2.setMediaController(mediaController2)
        videoView2.setVideoURI(Uri.parse(videoPath2))
        videoView2.start()  */
        /*val retriever = MediaMetadataRetriever()
        retriever.setDataSource(this, Uri.parse(videoPath2))
        val durationMs = retriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_DURATION)?.toLong()
        val durationFormatted = durationMs?.let { formatDuration(it) }
        val videoView1Length = findViewById<TextView>(R.id.videoView1Length)
        videoView1Length.text = durationFormatted!!.split("\u00B5")[0] + "s"
        retriever.release() */


        val subLessonList = "{data : [{\"sub_lesson_name\":\"Monday\", \"video\":\"sign_lang_video\"}, {\"sub_lesson_name\":\"Tuesday\", \"video\":\"sign_lang_video2\"}] }"
        var subLessonListString = ""
        val recyclerview = findViewById<RecyclerView>(R.id.subLessonRecyclerView)
        recyclerview.layoutManager = LinearLayoutManager(this)

        // ArrayList of class ItemsViewModel
        val subLessonData = ArrayList<SubLessonViewModel>()

        try {
            val jsonObject = JSONObject(subLessonList)
            val subLessonsArray = jsonObject.getJSONArray("data")

            for (i in 0 until subLessonsArray.length()) {
                val subLessonObject = subLessonsArray.getJSONObject(i)
                val subLessonName = subLessonObject.getString("sub_lesson_name")
                val video = subLessonObject.getString("video")
                val res = resources
                val resourceId = res.getIdentifier(video, "raw", packageName)
                val retriever = MediaMetadataRetriever()
                val videoPath = "android.resource://" + "com.example.androidnativeapp1" + "/" + resourceId
                retriever.setDataSource(this, Uri.parse(videoPath))
                val durationMs = retriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_DURATION)?.toLong()
                val durationFormatted = durationMs?.let { formatDuration(it) }
                val subLessonLength = durationFormatted!!.split("\u00B5")[0] + "s"
                retriever.release()
                subLessonData.add(SubLessonViewModel(resourceId,"$subLessonName", subLessonLength, "$lessonName"))
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }

        val adapter = SubLessonAdapter(subLessonData)
        recyclerview.adapter = adapter

        val backButton: TextView = findViewById(R.id.backButton)
        backButton.setOnClickListener {
            finish()
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