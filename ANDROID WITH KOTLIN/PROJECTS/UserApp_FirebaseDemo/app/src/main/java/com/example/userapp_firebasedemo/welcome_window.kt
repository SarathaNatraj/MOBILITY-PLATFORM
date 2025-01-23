package com.example.userapp_firebasedemo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.userapp_firebasedemo.databinding.ActivityWelcomeWindowBinding


class welcome_window : AppCompatActivity() {
    private lateinit var bind: ActivityWelcomeWindowBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bind= ActivityWelcomeWindowBinding.inflate(layoutInflater)
        setContentView(bind.root)
        var value=intent.getStringExtra("name")
        bind.uname.text=value
        bind.logout.setOnClickListener {
            startActivity(Intent(this,login_form::class.java))
        }
    }
}