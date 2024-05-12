package com.example.mycleanmarketapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.auth.FirebaseAuth

class ProfilePage : AppCompatActivity() {

    lateinit var editPass : EditText
    lateinit var editEmail : EditText
    lateinit var  btnLogout : Button

    private val firebaseAuth = FirebaseAuth.getInstance()
    private val firebaseUser = firebaseAuth.currentUser

    override fun onStart() {
        super.onStart()
        if(firebaseUser == null){
            Toast.makeText(
                applicationContext,
                "KONTOL",
                Toast.LENGTH_LONG
            ).show()
            startActivity(Intent(this, MainActivity::class.java))
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile_page)
        btnLogout = findViewById(R.id.log_out_btn)
        editEmail = findViewById(R.id.email_input)


        editEmail.setHint(firebaseUser!!.email.toString())

        btnLogout.setOnClickListener {
            firebaseAuth.signOut()
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }

    }
}