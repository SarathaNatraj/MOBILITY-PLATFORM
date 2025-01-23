package com.example.userapp_firebasedemo
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.userapp_firebasedemo.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.FirebaseApp

import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
class MainActivity : AppCompatActivity() {
    lateinit var binding : ActivityMainBinding
    private lateinit var db : DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        // Initialize Firebase
        FirebaseApp.initializeApp(this)


        binding.btnRigis.setOnClickListener {
            var name=binding.ed1.text.toString()
            var username=binding.ed2.text.toString()
            var password=binding.ed3.text.toString()
            if(name.isNotEmpty() && username.isNotEmpty() && password.isNotEmpty()) {
                db = FirebaseDatabase.getInstance().getReference("Users")
           //     replace('.', '%2E')
               name= name.replace(".","%2E")
                val user = Users(name, username, password)
                //setValue -> insert
                db.child(username).setValue(user).addOnSuccessListener {
                    var ad = AlertDialog.Builder(this)
                    ad.setTitle("Message")
                    ad.setMessage("Account registered successfully")
                    ad.setPositiveButton("Ok", null)
                    ad.show()
                    binding.ed1.text.clear() //binding.ed1.setText("")
                    binding.ed2.text.clear()
                    binding.ed3.text.clear()
                }.addOnFailureListener {
                    var ad = AlertDialog.Builder(this)
                    ad.setTitle("Message")
                    ad.setMessage("Account not register")
                    ad.setPositiveButton("Ok", null)
                    ad.show()
                }
            }else{
               // Snackbar.make(this, "", Snackbar.LENGTH_SHORT)
                Snackbar.make(binding.root, "All fields required!", Snackbar.LENGTH_SHORT).show()
                //Toast.makeText(this, "All fields required!", Toast.LENGTH_SHORT).show()
            }
        }
        binding.loginLink.setOnClickListener {
            val intent=Intent(this,login_form::class.java)
            startActivity(intent)
        }
    }
}