package com.example.mycleanmarketapp

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mycleanmarketapp.databinding.ActivityMainBinding
import com.example.mycleanmarketapp.model.Product


class CartPage : AppCompatActivity() {

    private lateinit var listProductAdapter: ListRowCartAdapter
    private lateinit var recyclerView: RecyclerView
    private val list = ArrayList<Product>()
    private lateinit var binding: ActivityMainBinding
    private lateinit var total : TextView
    var totalPayment = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_cart_page)

        binding.rvProduct.setHasFixedSize(true)



        total = findViewById(R.id.cart_total)
        list.addAll(getListProduct())
        showRecyclerList()
        updateTotal()



        recyclerView = findViewById(R.id.rv_product_cart)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)

        listProductAdapter = ListRowCartAdapter(list)
        recyclerView.adapter = listProductAdapter

        recyclerClick()

    }

    fun getListProduct(): ArrayList<Product> {
        val dataId = resources.getStringArray(R.array.data_id)
        val dataName = resources.getStringArray(R.array.data_name)
        val dataDescription = resources.getStringArray(R.array.data_description)
        val dataPrice = resources.getIntArray(R.array.data_price)
        val dataPhoto = resources.getStringArray(R.array.data_photo)
        Log.i("NumberGenerated", "HARGA")
        val listProduct = ArrayList<Product>()
        for (position in dataName.indices) {
            val product = Product(dataId[position], dataName[position],dataDescription[position], 3000, 1, dataPhoto[position])
            listProduct.add(product)
            //Log.i("CART_CART_CART_CART", "MASUK_MASUK_MASUK_MASUK"+product.name);
        }


        return listProduct
    }

    private fun updateTotal(){
        totalPayment = 0
        list.forEach {
            totalPayment += it.price * it.quantity
        }

        total.setText("" + totalPayment)
        Log.i("CART_CART_CART_CART", "MASUK_MASUK_MASUK_MASUK: "+ totalPayment);
    }
    private fun showRecyclerList() {
        binding.rvProduct.layoutManager = LinearLayoutManager(this)
        val listProductAdapter = ListRowCartAdapter(list)
        binding.rvProduct.adapter = listProductAdapter


    }

    private fun recyclerClick(){
        listProductAdapter.onItemClick = {
                list.remove(it)
                recyclerView.adapter!!.notifyDataSetChanged()
                updateTotal()
        }

        
    }

}