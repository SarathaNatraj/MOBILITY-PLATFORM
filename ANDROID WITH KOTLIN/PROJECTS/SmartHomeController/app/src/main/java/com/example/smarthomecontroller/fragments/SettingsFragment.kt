package com.example.smarthomecontroller.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment

import com.example.smarthomecontroller.R

class SettingsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_settings, container, false)

        val deviceNameInput = view.findViewById<EditText>(R.id.input_device_name)

        // Handle settings updates
        view.findViewById<View>(R.id.button_save_settings).setOnClickListener {
            val deviceName = deviceNameInput.text.toString()
            if (deviceName.isNotBlank()) {
                Toast.makeText(context, "Device name saved: $deviceName", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(context, "Please enter a device name", Toast.LENGTH_SHORT).show()
            }
        }

        return view
    }
}
