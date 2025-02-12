package com.example.groceryshoppinglistapp.data


import androidx.room.*

@Dao
interface GroceryDao {
    @Query("SELECT * FROM grocery_items")
    fun getAllItems(): List<GroceryItem>

    @Insert
    suspend fun insertItem(item: GroceryItem)

    @Delete
    suspend fun deleteItem(item: GroceryItem)

    @Update
    suspend fun updateItem(item: GroceryItem)
}

