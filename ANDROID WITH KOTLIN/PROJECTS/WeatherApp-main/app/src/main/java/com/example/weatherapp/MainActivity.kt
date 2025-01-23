package com.example.weatherapp

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.weatherapp.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Navigate to weather details
        binding.btnFetchWeather.setOnClickListener {
            fetchWeather("London")
        }
    }

    private fun fetchWeather(city: String) {
        val apiKey = "87655c9e85dfdc79596e239bf82090a3"
        CoroutineScope(Dispatchers.IO).launch {
            val response = RetrofitInstance.weatherApi.getWeather(city, apiKey)
            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    val weatherResponse = response.body()
                    displayWeather(weatherResponse)
                } else {
                    showError()
                }
            }
        }
    }

    private fun displayWeather(weatherResponse: WeatherResponse?) {
        if (weatherResponse != null) {
            binding.tvWeather.text = "Temp: ${weatherResponse.main.temp}°C, ${weatherResponse.weather[0].description}"
        }
    }

    private fun showError() {
        binding.tvWeather.text = "Error fetching weather data"
    }

}
