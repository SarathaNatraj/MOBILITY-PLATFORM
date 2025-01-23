package com.example.news.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.news.R
import com.example.news.model.User

class UserAdapter(private val user: List<User>, private val onClick:(User)->Unit) :RecyclerView.Adapter<UserAdapter.UserViewHolder>(){
    inner class UserViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        val titleTextView:TextView=itemView.findViewById(R.id.textView)
        fun click(user: User){
            itemView.setOnClickListener{
                onClick(user)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val view=LayoutInflater.from(parent.context).inflate(R.layout.item_view,parent,false)
        return UserViewHolder(view)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val person=user[position]
        holder.titleTextView.text=person.name
        holder.click(person)
    }

    override fun getItemCount(): Int =user.size
}