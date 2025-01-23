package com.example.smarthomecontroller

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.smarthomecontroller.databinding.ActivityMainBinding
import androidx.work.*
import java.util.concurrent.TimeUnit
import com.example.smarthomecontroller.workmanager.SyncWorker
import dagger.Provides
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    @get:Provides
     lateinit var navController: NavController
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
      //  binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_main) //R.layout.activity_main

        //  setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }


        //initialising the Navigation controller
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController

        // Setup ActionBar with NavController
        setupActionBarWithNavController(navController)

//Day 7: WorkManager for Background Tasks
//Task 1: Implement WorkManager to periodically sync device states with the server.
//Task 2: Use WorkManager to schedule tasks for device automation based on user-defined rules

        // Define user-defined rules
        val userRules = "Turn off Wi-Fi at night"

        // Create input data for the Worker
        val inputData = Data.Builder()
            .putString("userRules", userRules)
            .build()

        // Create a periodic work request with Worker class,
        val syncWorkRequest = PeriodicWorkRequestBuilder<SyncWorker>(15, TimeUnit.MINUTES)
            .setInputData(inputData)

            .build()

        // Enqueue the work request
        WorkManager.getInstance(this).enqueueUniquePeriodicWork(
            "DeviceSyncWork",
            ExistingPeriodicWorkPolicy.REPLACE,
            syncWorkRequest
        )
//observe workmanager task -> optionall
        WorkManager.getInstance(this).getWorkInfosForUniqueWorkLiveData("DeviceSyncWork")
            .observe(this) { workInfos ->
                workInfos?.forEach { workInfo ->
                    Log.d("MainActivity", "Work Status: ${workInfo.state}")
                }
            }




    }
}