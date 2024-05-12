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
import com.google.firebase.auth.userProfileChangeRequest

class RegisterPage : AppCompatActivity() {
    lateinit var editName : EditText
    lateinit var editPhone : EditText
    lateinit var editPass : EditText
    lateinit var editEmail : EditText
    lateinit var editAddress: EditText
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
        setContentView(R.layout.activity_register_page)
        editName = findViewById(R.id.name_input)
        editPhone = findViewById(R.id.phone_number_input)
        editEmail = findViewById(R.id.email_input)
        editPass = findViewById(R.id.password_input)
        editAddress = findViewById(R.id.address_input)
        btnRegister = findViewById(R.id.register_btn)
        btnLogin = findViewById(R.id.to_log_in_btn)


        btnLogin.setOnClickListener {
            startActivity(Intent(this, LoginPage::class.java))
            finish()
        }

        btnRegister.setOnClickListener {
            if(editName.text.isNotEmpty() && editEmail.text.isNotEmpty() && editPhone.text.isNotEmpty() && editAddress.text.isNotEmpty() && editPass.text.isNotEmpty()){
                processRegister()
            } else {
                Toast.makeText(this, "Silahkan isi semua data", Toast.LENGTH_SHORT).show()
            }
        }

    }

    fun processRegister(){
        val name = editName.text.toString()
        val email = editEmail.text.toString()
        val pass = editPass.text.toString()
        val phone = editPhone.text.toString()
        val address = editAddress.text.toString()

        fireBaseAuth.createUserWithEmailAndPassword(email, pass)
            .addOnCompleteListener { task ->
                if(task.isSuccessful){
                    val userUpdateProfile = userProfileChangeRequest {
                        displayName = name
                    }

                    val user = task.result.user
                    user!!.updateProfile(userUpdateProfile)
                        .addOnCompleteListener {
                            startActivity(Intent(this, MainActivity::class.java))
                        }
                        .addOnFailureListener { error2 ->
                            Toast.makeText(this, error2.localizedMessage, Toast.LENGTH_SHORT).show()
                        }
                }
            }
            .addOnFailureListener{error ->
                Toast.makeText(this, error.localizedMessage, Toast.LENGTH_SHORT).show()

            }

    }
}