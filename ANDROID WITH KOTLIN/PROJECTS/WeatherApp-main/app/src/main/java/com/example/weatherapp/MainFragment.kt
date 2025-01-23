package com.example.weatherapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import com.example.weatherapp.databinding.FragmentWeatherDetailsBinding


class MainFragment : Fragment(R.layout.fragment_weather_details) {

    private lateinit var binding: FragmentWeatherDetailsBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentWeatherDetailsBinding.bind(view)

        // Get weather data from arguments
        val weatherData = arguments?.getString("weather_data")
        binding.tvWeatherDetails.text = weatherData ?: "No weather details available"

        // Back button functionality
        binding.btnBack.setOnClickListener {
            requireActivity().onBackPressedDispatcher.onBackPressed()
        }
    }
}
