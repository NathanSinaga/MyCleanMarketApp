package com.example.mycleanmarketapp

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.mycleanmarketapp.model.Product

class ProductPage : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_page)

        val getData = intent.getParcelableExtra<Product>("android")
        if(getData != null){
            val detilGambar: ImageView = findViewById(R.id.detilGambar)
            val detilNama: TextView = findViewById(R.id.detilNama)
            val detilHarga: TextView = findViewById(R.id.detilHarga)
            val detilDeskripsi: TextView = findViewById(R.id.detilDeskripsi)


            Glide.with(this).load(getData.photo).apply(RequestOptions().override(1000, 1000)).into(findViewById(R.id.detilGambar))
            detilNama.text = getData.name
            detilHarga.text = "" + getData.price
            detilDeskripsi.text = getData.description
        }
    }
}