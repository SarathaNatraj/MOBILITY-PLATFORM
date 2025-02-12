package com.example.groceryshoppinglistapp.ui


import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.groceryshoppinglistapp.R
import com.example.groceryshoppinglistapp.adapter.GroceryAdapter
import com.example.groceryshoppinglistapp.data.GroceryDatabase
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HomeFragment : Fragment() {

    private lateinit var groceryAdapter: GroceryAdapter
    private lateinit var db: GroceryDatabase

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View {
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        db = GroceryDatabase.getDatabase(requireContext())
        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerView)
        val fabAddItem = view.findViewById<FloatingActionButton>(R.id.fabAdd)
        CoroutineScope(Dispatchers.IO).launch {

            val items = db.groceryDao().getAllItems()
            withContext(Dispatchers.Main) {
                // Update UI on Main Thread
                groceryAdapter = GroceryAdapter(items.toMutableList(), db)

                recyclerView.layoutManager = LinearLayoutManager(requireContext())
                recyclerView.adapter = groceryAdapter
            }
        }




        fabAddItem.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, AddItemFragment())
                .addToBackStack(null)
                .commit()
        }

        // Swipe to delete
        val itemTouchHelper = ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
            override fun onMove(rv: RecyclerView, vh: RecyclerView.ViewHolder, target: RecyclerView.ViewHolder) = false
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                CoroutineScope(Dispatchers.IO).launch {


                    val position = viewHolder.adapterPosition
                    val item = groceryAdapter.removeItem(position)
                    db.groceryDao().deleteItem(item)
                    Toast.makeText(requireContext(), "Item deleted", Toast.LENGTH_SHORT).show()
                }
            }
        })

        itemTouchHelper.attachToRecyclerView(recyclerView)

        return view
    }
}
