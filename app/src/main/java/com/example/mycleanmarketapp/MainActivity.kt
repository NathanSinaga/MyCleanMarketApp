package com.example.mycleanmarketapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.PopupMenu
import android.widget.SearchView
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.drawerlayout.widget.DrawerLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mycleanmarketapp.databinding.ActivityMainBinding
import com.google.android.material.navigation.NavigationView
import java.util.Locale
import androidx.drawerlayout.widget.DrawerLayout.DrawerListener as DrawerListener

class MainActivity : AppCompatActivity() {

    private lateinit var listProductAdapter: ListProductAdapter
    private lateinit var recyclerView: RecyclerView
    private lateinit var searchView : androidx.appcompat.widget.SearchView
    private lateinit var binding: ActivityMainBinding
    private var searchList : ArrayList<Product> = ArrayList()
    private val list = ArrayList<Product>()
    //private val productViewed = Product("1", "Indomie Ghaib", "Indomie yang tidak pernah dilihat mata", "Rp. 10.000","https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQf5Oiwd842E3iWMustkMn1TMDIrEgdUnaRITakR-akXw&s")
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var navView: NavigationView



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
                        if(it.name.toLowerCase(Locale.getDefault()).contains(searchText)) {
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
        val dataId = resources.getStringArray(R.array.data_id)
        val dataName = resources.getStringArray(R.array.data_name)
        val dataDescription = resources.getStringArray(R.array.data_description)
        val dataPrice = resources.getIntArray(R.array.data_price)
        val dataPhoto = resources.getStringArray(R.array.data_photo)
        Log.i("NumberGenerated", "HARGA")
        val listProduct = ArrayList<Product>()
        for (position in dataName.indices) {
            val product = Product(dataId[position], dataName[position],dataDescription[position], 3000,dataPhoto[position])
            listProduct.add(product)
            //Log.i("NumberGenerated", ""+product.price);
        }


        //listProduct.add(productViewed)
        return listProduct
    }

    private fun showRecyclerSearchList() {
        binding.rvProduct.layoutManager = LinearLayoutManager(this)
        val listProductAdapter = ListProductAdapter(searchList)
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

        navView.setNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.nav_profile -> {
                    Toast.makeText(applicationContext, "Berhasil Tekan Edit Profile", Toast.LENGTH_LONG).show()
                    val moveIntent = Intent(this, ProfilePage::class.java)
                    startActivity(Intent(moveIntent))
                }
                R.id.nav_tracking -> {
                    Toast.makeText(applicationContext, "Berhasil Tekan Tracking", Toast.LENGTH_LONG).show()
                    //val moveIntent = Intent(this@MainActivity, LoginPage::class.java)
                    //startActivity(moveIntent)
                }
                R.id.nav_history -> {
                    Toast.makeText(applicationContext, "Berhasil Tekan History", Toast.LENGTH_LONG).show()
                    //val moveIntent = Intent(this@MainActivity, LoginPage::class.java)
                    //startActivity(moveIntent)
                }
                R.id.nav_customer_service -> {
                    Toast.makeText(applicationContext, "Berhasil Tekan CS", Toast.LENGTH_LONG).show()
                }
                R.id.nav_terms_and_conditions -> {
                    Toast.makeText(applicationContext, "Berhasil Tekan Kebijakan", Toast.LENGTH_LONG).show()

                }

            }
            true

        }

    }

}