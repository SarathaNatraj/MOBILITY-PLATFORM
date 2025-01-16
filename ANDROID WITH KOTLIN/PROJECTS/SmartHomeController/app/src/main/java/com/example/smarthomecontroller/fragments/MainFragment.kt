package com.example.smarthomecontroller.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Switch
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.smarthomecontroller.R

class MainFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_main, container, false)

        val lightSwitch = view.findViewById<Switch>(R.id.switch_light)
        val thermostatButton = view.findViewById<Button>(R.id.button_thermostat)
        val settingsButton = view.findViewById<Button>(R.id.button_settings)
        val dashboardButton = view.findViewById<Button>(R.id.button_dashboard)

        // Handle smart device controls
        lightSwitch.setOnCheckedChangeListener { _, isChecked ->
            // Toggle light on/off
        }

        thermostatButton.setOnClickListener {
            // Open thermostat settings
        }

        settingsButton.setOnClickListener {
            // Navigate to SettingsFragment
            findNavController().navigate(R.id.action_mainFragment_to_settingsFragment2)
        }
        dashboardButton.setOnClickListener {
            // Navigate to SettingsFragment
            findNavController().navigate(R.id.action_mainFragment_to_dashboardFragment)
        }
        return view
    }
}
