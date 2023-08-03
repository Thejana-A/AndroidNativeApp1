package com.example.androidnativeapp1.video_chat

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
            intent.putExtra("contact_name", ContactViewModel.name);
            holder.viewChatConversation.context.startActivity(intent)
        }
        holder.inviteFriendButton.setOnClickListener {
            val intent = Intent(holder.inviteFriendButton.context, ContactList::class.java)
            intent.putExtra("invite_status", "true");
            holder.inviteFriendButton.context.startActivity(intent)
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
        val inviteFriendButton: Button = itemView.findViewById(R.id.inviteFriendButton)
    }

}
