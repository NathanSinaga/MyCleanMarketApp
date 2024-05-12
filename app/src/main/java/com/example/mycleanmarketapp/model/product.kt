package com.example.mycleanmarketapp.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class product (
    var id: String,
    var ProductName: String,
    var description: String,
    var price: Int,
    var quantity: Int,
    var photo: String

) : Parcelable
