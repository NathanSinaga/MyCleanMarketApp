package com.example.mycleanmarketapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.PopupMenu
import android.widget.Toast
import androidx.drawerlayout.widget.DrawerLayout
import androidx.drawerlayout.widget.DrawerLayout.DrawerListener as DrawerListener

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        drawer()
    }

    private fun drawer(){
        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        val imageProf : ImageView = findViewById(R.id.expanded_menu)

        imageProf.setOnClickListener(){
            drawerLayout.open()
        }
    }

}