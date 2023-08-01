package com.example.androidnativeapp1.video_chat

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.androidnativeapp1.R

class ContactAdapter(private val mList: List<ContactViewModel>) : RecyclerView.Adapter<ContactAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.card_view_contact, parent, false)
        return ViewHolder(view)
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val ContactViewModel = mList[position]
        holder.profilePhoto.setImageResource(ContactViewModel.image)
        holder.name.text = ContactViewModel.name
        holder.contactNo.text = ContactViewModel.contact_no
        holder.viewChatConversation.setOnClickListener {
            val intent = Intent(holder.viewChatConversation.context, Chat::class.java)
            holder.viewChatConversation.context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return mList.size
    }

    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val profilePhoto: ImageView = itemView.findViewById(R.id.profilePhoto)
        val name: TextView = itemView.findViewById(R.id.name)
        val contactNo: TextView = itemView.findViewById(R.id.contactNo)
        val viewChatConversation: CardView = itemView.findViewById(R.id.viewChatConversation)

    }
}
