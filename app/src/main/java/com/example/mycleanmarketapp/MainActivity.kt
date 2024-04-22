package com.example.mycleanmarketapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.PopupMenu
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.drawerlayout.widget.DrawerLayout
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mycleanmarketapp.databinding.ActivityMainBinding
import com.google.android.material.navigation.NavigationView
import androidx.drawerlayout.widget.DrawerLayout.DrawerListener as DrawerListener

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val list = ArrayList<Product>()
    private val productViewed = Product("1", "Indomie Ghaib", "Indomie yang tidak pernah dilihat mata", "Rp. 10.000","https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQf5Oiwd842E3iWMustkMn1TMDIrEgdUnaRITakR-akXw&s")
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var navView: NavigationView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.rvProduct.setHasFixedSize(true)

        drawer()

        list.addAll(getListProduct())
        showRecyclerList()
        popupMenuMain()
    }


    fun setCurrentProductViewed(id: String, name : String, description : String, price : String, photo : String){
        productViewed.id = id
        productViewed.name = name
        productViewed.description = description
        productViewed.price = price
        productViewed.photo = photo
    }

    fun getListProduct(): ArrayList<Product> {
        val dataId = resources.getStringArray(R.array.data_id)
        val dataName = resources.getStringArray(R.array.data_name)
        val dataDescription = resources.getStringArray(R.array.data_description)
        val dataPrice = resources.getStringArray(R.array.data_price)
        val dataPhoto = resources.getStringArray(R.array.data_photo)

        val listProduct = ArrayList<Product>()
        for (position in dataName.indices) {
            val product = Product(dataId[position], dataName[position],dataDescription[position], dataPrice[position],dataPhoto[position])
            listProduct.add(product)
        }

        listProduct.add(productViewed)
        return listProduct
    }
    private fun showRecyclerList() {
        binding.rvProduct.layoutManager = LinearLayoutManager(this)
        val listHeroAdapter = ListProductAdapter(list)
        binding.rvProduct.adapter = listHeroAdapter
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
                R.id.nav_wishlist -> {
                    Toast.makeText(applicationContext, "Berhasil Tekan Wishlist", Toast.LENGTH_LONG).show()
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