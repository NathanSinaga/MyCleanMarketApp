package com.example.mycleanmarketapp

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Product (
    var id: String,
    var name: String,
    var description: String,
    var price: Int,
    var photo: String

) : Parcelable
