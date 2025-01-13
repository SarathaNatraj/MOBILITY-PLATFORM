package com.example.smarthomecontrollerapp



import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val switchLivingRoomLight = findViewById<Switch>(R.id.switchLivingRoomLight)
        val switchBedroomLight = findViewById<Switch>(R.id.switchBedroomLight)
        val seekBarThermostat = findViewById<SeekBar>(R.id.seekBarThermostat)
        val tvThermostatValue = findViewById<TextView>(R.id.tvThermostatValue)
        val btnSaveSettings = findViewById<Button>(R.id.btnSaveSettings)

        // Update thermostat value dynamically
        seekBarThermostat.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                tvThermostatValue.text = "$progress°C"
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {}
            override fun onStopTrackingTouch(seekBar: SeekBar?) {}
        })

        // Save settings action
        btnSaveSettings.setOnClickListener {
            val livingRoomStatus = if (switchLivingRoomLight.isChecked) "ON" else "OFF"
            val bedroomStatus = if (switchBedroomLight.isChecked) "ON" else "OFF"
            val thermostatValue = tvThermostatValue.text

            Toast.makeText(
                this,
                "Settings Saved:\nLiving Room: $livingRoomStatus\nBedroom: $bedroomStatus\nThermostat: $thermostatValue",
                Toast.LENGTH_LONG
            ).show()
        }
    }
}
