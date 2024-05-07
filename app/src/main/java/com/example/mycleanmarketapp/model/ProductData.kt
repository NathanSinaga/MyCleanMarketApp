package com.example.mycleanmarketapp.model

data class ProductData (
    val product : List <Data>
) {
    data class Data (
        val id: String?,
        var name: String?,
        var description: String?,
        var price: Int?,
        var quantity: Int?,
        var photo: String?
    )
}