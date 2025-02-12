package com.example.groceryshoppinglistapp.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.example.groceryshoppinglistapp.R
import com.example.groceryshoppinglistapp.data.GroceryDatabase
import com.example.groceryshoppinglistapp.data.GroceryItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class AddItemFragment : Fragment() {
    private lateinit var groceryDatabase: GroceryDatabase
    private lateinit var editTextItemName: EditText
    private lateinit var editTextQuantity: EditText
    private lateinit var buttonSave: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_add_item, container, false)

        groceryDatabase = GroceryDatabase.getDatabase(requireContext())

        editTextItemName = view.findViewById(R.id.editTextItemName)
        editTextQuantity = view.findViewById(R.id.editTextQuantity)
        buttonSave = view.findViewById(R.id.buttonSave)

        buttonSave.setOnClickListener {
            saveItemToDatabase()
        }

        return view
    }

    private fun saveItemToDatabase() {
        val itemName = editTextItemName.text.toString().trim()
        val quantityText = editTextQuantity.text.toString().trim()

        if (itemName.isEmpty() || quantityText.isEmpty()) {
            Toast.makeText(requireContext(), "Please enter all fields", Toast.LENGTH_SHORT).show()
            return
        }

        val quantity = quantityText.toInt()

        val newItem = GroceryItem(name = itemName, quantity = quantity)

        lifecycleScope.launch(Dispatchers.IO) {
            groceryDatabase.groceryDao().insertItem(newItem)
            withContext(Dispatchers.Main) {
                Toast.makeText(requireContext(), "Item added successfully", Toast.LENGTH_SHORT).show()
                requireActivity().onBackPressed() // Navigate back
            }
        }
    }
}
