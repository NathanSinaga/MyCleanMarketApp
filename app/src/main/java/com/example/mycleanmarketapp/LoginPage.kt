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

class LoginPage : AppCompatActivity() {
    lateinit var editPass : EditText
    lateinit var editEmail : EditText
    lateinit var  btnLogin : Button
    lateinit var  btnRegister : Button

    var fireBaseAuth = FirebaseAuth.getInstance()

    override fun onStart() {
        super.onStart()
        if(fireBaseAuth.currentUser != null){
            startActivity(Intent(this, MainActivity::class.java))
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_page)
        editEmail = findViewById(R.id.email_input)
        editPass = findViewById(R.id.password_input)
        btnLogin = findViewById(R.id.login_btn)
        btnRegister = findViewById(R.id.to_register_btn)

        btnLogin.setOnClickListener {
            if(editEmail.text.isNotEmpty() && editPass.text.isNotEmpty()) {
                processLogin()
            }else{
                Toast.makeText(this, "Isi dulu", Toast.LENGTH_SHORT).show()
            }
        }

        btnRegister.setOnClickListener {
            startActivity(Intent(this, RegisterPage::class.java))
            finish()
        }
    }

    private fun processLogin(){
        val email = editEmail.text.toString()
        val password =  editPass.text.toString()

        fireBaseAuth.signInWithEmailAndPassword(email, password)
            .addOnSuccessListener {
                startActivity(Intent(this, MainActivity::class.java))
            }
            .addOnFailureListener{error ->
                Toast.makeText(this, error.localizedMessage, Toast.LENGTH_SHORT).show()
            }
    }
}