package com.example.userapp_firebasedemo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.userapp_firebasedemo.databinding.ActivityLoginFormBinding
import com.google.firebase.FirebaseApp
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class login_form : AppCompatActivity() {
    private lateinit var bind : ActivityLoginFormBinding
    private lateinit var db:DatabaseReference
    var username:String=""
    var password:String=""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Initialize Firebase
        FirebaseApp.initializeApp(this)

        bind= ActivityLoginFormBinding.inflate(layoutInflater)
        setContentView(bind.root)
        bind.regisLink.setOnClickListener {
            val intent= Intent(this,MainActivity::class.java)
            startActivity(intent)
        }
       bind.btnLogin.setOnClickListener{
           username=bind.logtxt.text.toString();
           password=bind.ed3.text.toString()
           if(username.isNotEmpty() && password.isNotEmpty()){
               fetch_data(username)
           }else{
               Toast.makeText(this,"Please fill all fields",Toast.LENGTH_SHORT).show()
           }
       }
    }

    private fun fetch_data(username: String) {


        db=FirebaseDatabase.getInstance().getReference("Users")

        //db.child(username).removeValue() - delete
        //db.child(username).setValue(user) - insert/update
        //accessing  data -> get
        db.child(username).get().addOnSuccessListener {
            if(it.exists()){
                var psswrd:String=it.child("pasword").value.toString()
                var name:String=it.child("name").value.toString()
                if(password.equals(psswrd)){
                    startActivity(Intent(this,welcome_window::class.java).putExtra("name",name))
                }else{
                    var ad = AlertDialog.Builder(this)
                    ad.setTitle("Message")
                    ad.setMessage("Failed to login")
                    ad.setPositiveButton("Ok", null)
                    ad.show()
                }
            }else{
                var ad = AlertDialog.Builder(this)
                ad.setTitle("Message")
                ad.setMessage("Failed to login")
                ad.setPositiveButton("Ok", null)
                ad.show()
            }
        }
    }
}