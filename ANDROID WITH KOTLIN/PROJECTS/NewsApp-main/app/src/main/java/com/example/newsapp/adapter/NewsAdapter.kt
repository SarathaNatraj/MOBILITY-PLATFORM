package com.example.newsapp.adapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.newsapp.R
import com.example.newsapp.model.Source

class NewsAdapter(private val onClick: (Source) -> Unit) :
    ListAdapter<Source, NewsAdapter.NewsViewHolder>(DiffCallback()) {

    inner class NewsViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val titleTextView: TextView = itemView.findViewById(R.id.titleTextView)

        fun bind(source: Source) {
            titleTextView.text = source.name
            itemView.setOnClickListener {
                onClick(source)
            }
        }
    }

    class DiffCallback : DiffUtil.ItemCallback<Source>() {
        override fun areItemsTheSame(oldItem: Source, newItem: Source): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Source, newItem: Source): Boolean {
            return oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item_news, parent, false)
        return NewsViewHolder(view)
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val source = getItem(position)
        holder.bind(source)
    }
}
