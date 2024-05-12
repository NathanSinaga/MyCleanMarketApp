package com.example.mycleanmarketapp.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Product (
    var ProductId: String,
    var ProductName: String,
    var ProductDesc: String,
    var ProductPrice: Int,
    var ProductQty: Int,
    var ProductPhoto: String

) : Parcelable
