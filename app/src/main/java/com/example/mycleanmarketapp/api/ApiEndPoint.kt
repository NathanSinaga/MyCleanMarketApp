package com.example.mycleanmarketapp.api

import com.example.mycleanmarketapp.model.Product
import retrofit2.Call
import retrofit2.http.GET
interface ApiEndPoint {
    @GET("retriveProduct.php")
    fun data() : Call<Product>

}