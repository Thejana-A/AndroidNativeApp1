package com.example.androidnativeapp1.learn

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.net.Uri
import android.view.animation.AlphaAnimation
import android.widget.Button
import android.widget.ImageView
import android.widget.MediaController
import android.widget.TextView
import android.widget.VideoView
import androidx.appcompat.view.menu.MenuView
import androidx.cardview.widget.CardView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.androidnativeapp1.R
import com.example.androidnativeapp1.learn.ListOfSubLessons

class SubLessonAdapter(private val mList: List<SubLessonViewModel>) : RecyclerView.Adapter<SubLessonAdapter.SubLessonViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SubLessonViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.card_view_sub_lesson, parent, false)
        return SubLessonViewHolder(view)
    }

    override fun onBindViewHolder(holder: SubLessonViewHolder, position: Int) {
        val SubLessonViewModel = mList[position]
        holder.subLessonName.text = SubLessonViewModel.sub_lesson_name
        holder.subLessonLength.text = SubLessonViewModel.sub_lesson_length
        val videoPath = "android.resource://" + "com.example.androidnativeapp1" + "/" + SubLessonViewModel.sub_lesson_video
        val videoView = holder.video
        val mediaController = MediaController(videoView.context)
        mediaController.setAnchorView(videoView)
        videoView.setMediaController(mediaController)
        videoView.setVideoURI(Uri.parse(videoPath))
        videoView.start()
        holder.selectSubLesson.setOnClickListener {
            val intent = Intent(holder.selectSubLesson.context, OngoingLesson::class.java)
            intent.putExtra("sub_lesson_name", SubLessonViewModel.sub_lesson_name);
            intent.putExtra("lesson_name", SubLessonViewModel.lesson_name);
            intent.putExtra("sub_lesson_video", videoPath);
            holder.selectSubLesson.context.startActivity(intent)
        }

    }

    override fun getItemCount(): Int {
        return mList.size
    }

    class SubLessonViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val video: VideoView = itemView.findViewById(R.id.videoView)
        val subLessonName: TextView = itemView.findViewById(R.id.subLessonName)
        val subLessonLength: TextView = itemView.findViewById(R.id.subLessonLength)
        val selectSubLesson: CardView = itemView.findViewById(R.id.selectSubLesson)
    }

}
