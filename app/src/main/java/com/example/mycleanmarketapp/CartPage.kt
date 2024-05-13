package com.example.mycleanmarketapp

import android.content.ContentValues
import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mycleanmarketapp.databinding.ActivityMainBinding
import com.example.mycleanmarketapp.model.Product
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.database
import com.google.firebase.database.getValue
import java.time.LocalDate
import java.time.LocalDateTime
import kotlin.random.Random


class CartPage : AppCompatActivity() {

    private lateinit var listProductAdapter: ListRowCartAdapter
    private lateinit var recyclerView: RecyclerView
    private val list = ArrayList<Product>()
    private lateinit var binding: ActivityMainBinding
    private lateinit var total : TextView
    var totalPayment = 0
    var database = Firebase.database
    var myRef = database.getReference("Product")
    var myRef2 = database.getReference("Transaction")
    private lateinit var  btnCheckOut : Button

    private val firebaseAuth = FirebaseAuth.getInstance()
    private val firebaseUser = firebaseAuth.currentUser
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_cart_page)

        binding.rvProduct.setHasFixedSize(true)



        btnCheckOut = findViewById(R.id.check_out_btn)
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

        btnCheckOut.setOnClickListener {
            myRef.removeValue()
                .addOnSuccessListener {
                    // Data successfully cleared
                    Log.d(TAG, "Data under 'Product' node cleared successfully")
                }
                .addOnFailureListener {
                    // Failed to clear data
                    Log.e(TAG, "Failed to clear data under 'Product' node", it)
                }


            val Transaction2 = HashMap<String, Any>()

            Transaction2["TransDate"] = "" + LocalDateTime.now()

            Transaction2["TransTotal"] = totalPayment

            val randomString = firebaseUser?.email.toString()
            val randomNumber = Random.nextInt(101)

            val id = "" + randomString + " / " + randomNumber + " / " + LocalDate.now()

            Transaction2["TransId"] = id



            val childUpdates = HashMap<String, Any>()


            childUpdates["" + randomNumber] = Transaction2


            myRef2.updateChildren(childUpdates)
            list.clear()
            showRecyclerList()
            updateTotal()

            Toast.makeText(applicationContext, "Berhasil Check-Out", Toast.LENGTH_LONG)
                .show()
        }


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
            if(product.ProductName == "Indomie Rendang") {
                listProduct.add(product)
            }
            if(product.ProductName == "Indomie Rendang Jumbo"){
                listProduct.add(product)
            }
            //Log.i("CART_CART_CART_CART", "MASUK_MASUK_MASUK_MASUK"+product.name);
        }

        listProduct.clear()


        myRef.addValueEventListener(object: ValueEventListener {

            override fun onDataChange(snapshot: DataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.

                snapshot.children.forEach { dataSnapshot ->
                    val value : Product? = dataSnapshot.getValue<Product>()
                    Log.i(ContentValues.TAG, "Value is: " + value.toString())
                    if (value != null) {
                        listProduct.add(value)
                        Log.i(ContentValues.TAG, value.toString())

                    }
                }
                recyclerView.adapter!!.notifyDataSetChanged()
                list.clear()
                list.addAll(listProduct)
                updateTotal()
            }

            override fun onCancelled(error: DatabaseError) {
                Log.i(ContentValues.TAG, "Failed to read value.", error.toException())
            }

        })


        return listProduct
    }

    private fun updateTotal(){
        totalPayment = 0
        list.forEach {
            totalPayment += it.ProductPrice * it.ProductQty
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