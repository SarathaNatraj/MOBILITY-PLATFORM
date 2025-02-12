package com.example.groceryshoppinglistapp.adapter



import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.groceryshoppinglistapp.R
import com.example.groceryshoppinglistapp.data.GroceryDatabase

import com.example.groceryshoppinglistapp.data.GroceryItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class GroceryAdapter(private val items: MutableList<GroceryItem>, private val db: GroceryDatabase) :
    RecyclerView.Adapter<GroceryAdapter.GroceryViewHolder>() {

    class GroceryViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val itemName: TextView = view.findViewById(R.id.itemName)
        val itemQuantity: TextView = view.findViewById(R.id.itemQuantity)
        val checkBox: CheckBox = view.findViewById(R.id.checkBox)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GroceryViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_grocery, parent, false)
        return GroceryViewHolder(view)
    }

    override fun onBindViewHolder(holder: GroceryViewHolder, position: Int) {
        val item = items[position]
        holder.itemName.text = item.name
        holder.itemQuantity.text = "Qty: ${item.quantity}"
        holder.checkBox.isChecked = item.isBought

        holder.checkBox.setOnCheckedChangeListener { _, isChecked ->
            CoroutineScope(Dispatchers.IO).launch {

                val updatedItem = item.copy(isBought = isChecked)
                db.groceryDao().updateItem(updatedItem)
            }
        }
    }

    override fun getItemCount() = items.size

    fun removeItem(position: Int): GroceryItem {
        val item = items.removeAt(position)
        notifyItemRemoved(position)
        return item
    }
}
