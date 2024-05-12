package com.example.mycleanmarketapp

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mycleanmarketapp.databinding.ActivityMainBinding
import com.example.mycleanmarketapp.model.Product
import com.example.mycleanmarketapp.model.Transaction

class HistoryPage : AppCompatActivity() {
    private lateinit var listTransAdapter: ListTransAdapter
    private lateinit var recyclerView: RecyclerView
    private val list = ArrayList<Transaction>()
    private lateinit var binding: ActivityMainBinding
    private lateinit var total : TextView
    var totalPayment = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_history_page)

        binding.rvProduct.setHasFixedSize(true)



        list.addAll(getListTrans())
        showRecyclerList()



        recyclerView = findViewById(R.id.rv_product_cart)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)

        listTransAdapter = ListTransAdapter(list)
        recyclerView.adapter = listTransAdapter

        recyclerClick()

    }

    fun getListTrans(): ArrayList<Transaction> {
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


        val trans1 = Transaction("x1/XIII/001/2024", "22 Mei 2024", listProduct)
        val trans2 = Transaction("x1/XIII/002/2024", "23 Mei 2024", listProduct)
        val listTrans = ArrayList<Transaction>()
        listTrans.add(trans1)
        listTrans.add(trans2)
        return listTrans
    }


    private fun showRecyclerList() {
        binding.rvProduct.layoutManager = LinearLayoutManager(this)
        val listTransAdapter = ListTransAdapter(list)
        binding.rvProduct.adapter = listTransAdapter


    }

    private fun recyclerClick(){
        listTransAdapter.onItemClick = {
            //val moveIntent = Intent(this@HistoryPage, HistoryPage2::class.java)
            //moveIntent.putExtra("android", it)
            //startActivity(moveIntent)
        }


    }
}