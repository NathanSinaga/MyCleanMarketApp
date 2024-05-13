package com.example.mycleanmarketapp

import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.mycleanmarketapp.model.Product
import com.google.firebase.Firebase
import com.google.firebase.database.database

class ProductPage : AppCompatActivity() {
    var database = Firebase.database
    var myRef = database.getReference("Product")
    lateinit var  btnAddProd : Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_page)
        btnAddProd = findViewById(R.id.add_to_car_btn)

        val getData = intent.getParcelableExtra<Product>("android")
        if(getData != null){
            val detilGambar: ImageView = findViewById(R.id.detilGambar)
            val detilNama: TextView = findViewById(R.id.detilNama)
            val detilHarga: TextView = findViewById(R.id.detilHarga)
            val detilDeskripsi: TextView = findViewById(R.id.detilDeskripsi)


            Glide.with(this).load(getData.ProductPhoto).apply(RequestOptions().override(1000, 1000)).into(findViewById(R.id.detilGambar))
            detilNama.text = getData.ProductName
            detilHarga.text = "" + getData.ProductPrice
            detilDeskripsi.text = getData.ProductDesc
        }

        btnAddProd.setOnClickListener {
            val productA2 = HashMap<String, Any>()
            if (getData != null) {
                productA2["ProductDesc"] = getData.ProductDesc
            }
            if (getData != null) {
                productA2["ProductId"] = getData.ProductId
            }
            if (getData != null) {
                productA2["ProductName"] = getData.ProductName
            }
            if (getData != null) {
                productA2["ProductPhoto"] = getData.ProductPhoto
            }
            if (getData != null) {
                productA2["ProductPrice"] = getData.ProductPrice
            }
            productA2["ProductQty"] = 1

            val childUpdates = HashMap<String, Any>()
            if (getData != null) {
                childUpdates[getData.ProductId] = productA2
            }

            myRef.updateChildren(childUpdates)
            Toast.makeText(applicationContext, "Berhasil Add-To-Cart", Toast.LENGTH_LONG)
                .show()
        }
    }
}