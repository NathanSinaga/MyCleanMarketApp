package com.example.mycleanmarketapp.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Product @JvmOverloads constructor(
    var ProductId: String = "",
    var ProductName: String = "",
    var ProductDesc: String = "",
    var ProductPrice: Int = 0,
    var ProductQty: Int = 0,
    var ProductPhoto: String = ""

) : Parcelable
