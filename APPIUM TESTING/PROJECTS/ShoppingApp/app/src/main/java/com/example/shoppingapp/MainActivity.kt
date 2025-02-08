package com.example.shoppingapp


import android.os.Bundle
import android.util.Log
import android.view.GestureDetector
import android.view.MotionEvent

import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.shoppingapp.ProductAdapter

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var productAdapter: ProductAdapter
    private val productList = mutableListOf<String>() // Mock product data

    private var currentCategory = 0 // Current category index

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recyclerView)
        productAdapter = ProductAdapter(productList)

        recyclerView.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        recyclerView.adapter = productAdapter

        loadProducts(currentCategory)

        val gestureDetector = GestureDetector(this, object : GestureDetector.SimpleOnGestureListener() {

            override fun onFling(
                e1: MotionEvent?,
                e2: MotionEvent,
                velocityX: Float,
                velocityY: Float
            ): Boolean {
                if (e1 != null && e2 != null) {
                    val diffX = e2.x - e1.x
                    val swipeThreshold = 100
                    val velocityThreshold = 100

                    if (Math.abs(diffX) > swipeThreshold && Math.abs(velocityX) > velocityThreshold) {
                        if (diffX > 0) {
                            onSwipeRight()
                        } else {
                            onSwipeLeft()
                        }
                        return true
                    }
                }
                return false
            }
        })

        recyclerView.setOnTouchListener { _, event ->
            gestureDetector.onTouchEvent(event)
        }
    }

    private fun loadProducts(categoryIndex: Int) {
        productList.clear()
        productList.addAll(getMockProducts(categoryIndex))
        productAdapter.notifyDataSetChanged()
    }

    private fun onSwipeRight() {
        if (currentCategory > 0) {
            currentCategory--
            Log.d("Swipe", "Swiped to previous category")
            loadProducts(currentCategory)
        }
    }

    private fun onSwipeLeft() {
        if (currentCategory < 2) { // Assuming 3 categories for demo
            currentCategory++
            Log.d("Swipe", "Swiped to next category")
            loadProducts(currentCategory)
        }
    }

    private fun getMockProducts(category: Int): List<String> {
        return when (category) {
            0 -> listOf("Product A1", "Product A2", "Product A3")
            1 -> listOf("Product B1", "Product B2", "Product B3")
            2 -> listOf("Product C1", "Product C2", "Product C3")
            else -> emptyList()
        }
    }
}
