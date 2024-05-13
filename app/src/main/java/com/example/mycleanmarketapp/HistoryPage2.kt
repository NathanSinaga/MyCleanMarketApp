package com.example.mycleanmarketapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mycleanmarketapp.databinding.ActivityMainBinding
import com.example.mycleanmarketapp.model.Product
import com.example.mycleanmarketapp.model.Transaction

class HistoryPage2 : AppCompatActivity() {

    private lateinit var listProductAdapter: ListProductAdapter
    private var recyclerView: RecyclerView = findViewById(R.id.aku_disini)
    private val list = ArrayList<Product>()
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_history_page2)


        val getData = intent.getParcelableExtra<Transaction>("android")
        binding.rvProduct.setHasFixedSize(true)




        showRecyclerList()




        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)

        listProductAdapter = ListProductAdapter(list)
        recyclerView.adapter = listProductAdapter

        recyclerClick()
    }

    private fun showRecyclerList() {
        binding.rvProduct.layoutManager = LinearLayoutManager(this)
        val listProductAdapter = ListRowCartAdapter(list)
        binding.rvProduct.adapter = listProductAdapter


    }

    private fun recyclerClick(){
        listProductAdapter.onItemClick = {

        }


    }
}