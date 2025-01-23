package com.example.personinfo.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.personinfo.R
import com.example.personinfo.model.Person

class PeopleAdapter(
    private val people: List<Person>,
    private val onClick: (Person) -> Unit
) : RecyclerView.Adapter<PeopleAdapter.PersonViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PersonViewHolder {
        // Inflate the item layout manually
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_view, parent, false)
        return PersonViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: PersonViewHolder, position: Int) {
        val person = people[position]
        holder.bind(person)
    }

    override fun getItemCount(): Int = people.size

    inner class PersonViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val nameTextView: TextView = itemView.findViewById(R.id.nameTextView)

        fun bind(person: Person) {
            // Bind the data to the views
            nameTextView.text = person.name

            // Set onClickListener
            itemView.setOnClickListener {
                onClick(person)  // Trigger the onClick action when the item is clicked
            }
        }
    }
}
