package com.example.calculatorapp



import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.calculatorapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnAdd.setOnClickListener {
            val num1 = binding.etFirstNumber.text.toString().toDoubleOrNull() ?: 0.0
            val num2 = binding.etSecondNumber.text.toString().toDoubleOrNull() ?: 0.0
            binding.tvResult.text = "Result: ${num1 + num2}"
        }

        binding.btnNavigate.setOnClickListener {
            val intent = Intent(this, SecondActivity::class.java)
            startActivity(intent)
        }

    }
}
