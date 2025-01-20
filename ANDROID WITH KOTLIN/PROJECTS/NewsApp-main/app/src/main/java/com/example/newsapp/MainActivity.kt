package com.example.newsapp

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupActionBarWithNavController

class MainActivity : AppCompatActivity() {

    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        Toast.makeText(this@MainActivity, "New Update", Toast.LENGTH_SHORT).show()
        val navHostFragment=supportFragmentManager.findFragmentById(R.id.nav_host_fragment)as NavHostFragment
        navController = navHostFragment.navController


        setupActionBarWithNavController(navController)
        Toast.makeText(this@MainActivity, "News 1", Toast.LENGTH_SHORT).show()
    }
}