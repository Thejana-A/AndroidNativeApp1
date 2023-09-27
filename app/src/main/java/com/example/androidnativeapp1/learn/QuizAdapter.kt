package com.example.androidnativeapp1.learn

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AlphaAnimation
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.view.menu.MenuView
import androidx.cardview.widget.CardView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.androidnativeapp1.R
import com.example.androidnativeapp1.learn.ListOfSubLessons


class QuizAdapter(private val mList: List<QuizViewModel>) : RecyclerView.Adapter<QuizAdapter.QuizViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuizViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.card_view_quiz, parent, false)
        return QuizViewHolder(view)
    }


    override fun onBindViewHolder(holder: QuizViewHolder, position: Int) {
        val QuizViewModel = mList[position]
        holder.quizName.text = QuizViewModel.name
        holder.linkToStartQuiz.text = QuizViewModel.complete_status
        holder.linkToStartQuiz.setOnClickListener {
            val intent = Intent(holder.linkToStartQuiz.context, OngoingQuiz::class.java)
            intent.putExtra("quiz_name", QuizViewModel.name);
            holder.linkToStartQuiz.context.startActivity(intent)
        }

    }

    override fun getItemCount(): Int {
        return mList.size
    }

    class QuizViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val quizName: TextView = itemView.findViewById(R.id.quizName)
        val linkToStartQuiz: Button = itemView.findViewById(R.id.linkToStartQuiz)
    }

}
