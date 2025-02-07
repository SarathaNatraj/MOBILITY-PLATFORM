package com.example.calculatorapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.calculatorapp.databinding.ActivitySecondBinding

class SecondActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySecondBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.textViewMessage.text = "Welcome to the Second Screen!"
    }
}
