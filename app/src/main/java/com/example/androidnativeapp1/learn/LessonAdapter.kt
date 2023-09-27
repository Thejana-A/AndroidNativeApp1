package com.example.androidnativeapp1.learn

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.androidnativeapp1.R


class LessonAdapter(private val mList: ArrayList<LessonViewModel>) : RecyclerView.Adapter<LessonAdapter.LessonViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LessonViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.card_view_lesson, parent, false)
        return LessonViewHolder(view)
    }


    override fun onBindViewHolder(holder: LessonViewHolder, position: Int) {
        val LessonViewModel = mList[position]
        holder.lessonName.text = LessonViewModel.name
        holder.completeStatus.text = LessonViewModel.complete_status
        holder.linkToListOfSubLessons.setOnClickListener {
            val intent = Intent(holder.linkToListOfSubLessons.context, ListOfSubLessons::class.java)
            intent.putExtra("lesson_name", LessonViewModel.name);
            holder.linkToListOfSubLessons.context.startActivity(intent)
        }

    }

    override fun getItemCount(): Int {
        return mList.size
    }

    class LessonViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val lessonName: TextView = itemView.findViewById(R.id.lessonName)
        val completeStatus: TextView = itemView.findViewById(R.id.completeStatus)
        val linkToListOfSubLessons: CardView = itemView.findViewById(R.id.linkToListOfSubLessons)
    }

}
