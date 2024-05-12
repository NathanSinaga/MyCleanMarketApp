package com.example.mycleanmarketapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.drawerlayout.widget.DrawerLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mycleanmarketapp.api.ApiRetroFit
import com.example.mycleanmarketapp.databinding.ActivityMainBinding
import com.example.mycleanmarketapp.model.Product
import com.example.mycleanmarketapp.model.ProductData
import com.google.android.material.navigation.NavigationView
import com.google.firebase.auth.FirebaseAuth
import java.util.Locale

class MainActivity : AppCompatActivity() {

    private val api by lazy { ApiRetroFit().endPoint }
    private lateinit var listProductAdapter: ListProductAdapter
    private lateinit var recyclerView: RecyclerView
    private lateinit var searchView : androidx.appcompat.widget.SearchView
    private lateinit var binding: ActivityMainBinding
    private var searchList : ArrayList<Product> = ArrayList()
    private val list = ArrayList<Product>()
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var navView: NavigationView
    private lateinit var headerEmail: TextView


    private val firebaseAuth = FirebaseAuth.getInstance()
    private val firebaseUser = firebaseAuth.currentUser
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)



        binding.rvProduct.setHasFixedSize(true)

        drawer()

        list.addAll(getListProduct())
        showRecyclerSearchList()
        popupMenuMain()



        recyclerView = findViewById(R.id.rv_product)
        searchView = findViewById(R.id.search_input)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)


        searchList.addAll(list)


        listProductAdapter = ListProductAdapter(searchList)
        recyclerView.adapter = listProductAdapter



        searchView.clearFocus()
        searchView.setOnQueryTextListener(object : androidx.appcompat.widget.SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                searchView.clearFocus()
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                searchList.clear()
                val searchText = newText!!.toLowerCase(Locale.getDefault())
                if(searchText.isNotEmpty()){
                    list.forEach {
                        if(it.ProductName.toLowerCase(Locale.getDefault()).contains(searchText)) {
                            searchList.add(it)

                        }
                        recyclerClick()
                    }
                } else {
                    searchList.clear()
                    searchList.addAll(list)
                }
                recyclerView.adapter!!.notifyDataSetChanged()
                return true
            }

        })


        recyclerClick()

    }





    fun getListProduct(): ArrayList<Product> {

        val listProduct = ArrayList<Product>()

        val prod : ProductData



        val dataId = resources.getStringArray(R.array.data_id)
        val dataName = resources.getStringArray(R.array.data_name)
        val dataDescription = resources.getStringArray(R.array.data_description)
        val dataPrice = resources.getIntArray(R.array.data_price)
        val dataPhoto = resources.getStringArray(R.array.data_photo)
        Log.i("NumberGenerated", "HARGA")

        for (position in dataName.indices) {
            val product = Product(dataId[position], dataName[position],dataDescription[position], 3000, 1, dataPhoto[position])
            listProduct.add(product)
            Log.i("NumberGenerated", ""+product.ProductPrice);
        }

        //listProduct.clear()

        //Masukan kode nya disini

        //Masukan kode nya disini


        return listProduct
    }

    private fun showRecyclerSearchList() {
        binding.rvProduct.layoutManager = LinearLayoutManager(this)
        val listProductAdapter = ListRowCartAdapter(searchList)
        binding.rvProduct.adapter = listProductAdapter
    }

    private fun recyclerClick(){
        listProductAdapter.onItemClick = {
            Toast.makeText(applicationContext, "Berhasil Tekan Product", Toast.LENGTH_LONG).show()
            val moveIntent = Intent(this@MainActivity, ProductPage::class.java)
            moveIntent.putExtra("android", it)
            startActivity(moveIntent)
        }
    }
    private fun drawer(){
        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        val imageProf : ImageView = findViewById(R.id.expanded_menu)

        imageProf.setOnClickListener(){
            drawerLayout.open()
        }
    }

    private fun popupMenuMain(){
        drawerLayout = findViewById(R.id.drawer_layout)
        navView = findViewById(R.id.nav_view)

        if(firebaseUser!=null) {
            navView.setNavigationItemSelectedListener { menuItem ->
                when (menuItem.itemId) {
                    R.id.nav_profile -> {
                        Toast.makeText(
                            applicationContext,
                            firebaseUser.email.toString(),
                            Toast.LENGTH_LONG
                        ).show()
                        val moveIntent = Intent(this, ProfilePage::class.java)
                        startActivity(Intent(moveIntent))
                    }

                    R.id.nav_cart -> {
                        Toast.makeText(applicationContext, "Berhasil Tekan Cart", Toast.LENGTH_LONG)
                            .show()
                        val moveIntent = Intent(this@MainActivity, CartPage::class.java)
                        startActivity(moveIntent)
                    }

                    R.id.nav_history -> {
                        Toast.makeText(
                            applicationContext,
                            "Berhasil Tekan History",
                            Toast.LENGTH_LONG
                        ).show()
                        val moveIntent = Intent(this@MainActivity, HistoryPage::class.java)
                        startActivity(moveIntent)
                    }

                    R.id.nav_terms_and_conditions -> {
                        Toast.makeText(
                            applicationContext,
                            "Berhasil Tekan Kebijakan",
                            Toast.LENGTH_LONG
                        ).show()

                    }

                }
                true

            }
        } else {

            startActivity(Intent(this, LoginPage::class.java))
        }

    }

}


