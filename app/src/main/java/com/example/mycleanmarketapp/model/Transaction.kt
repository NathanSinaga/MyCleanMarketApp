package com.example.mycleanmarketapp.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import java.util.Date

@Parcelize
data class Transaction (
    var id: String,
    var date: String,
    var item : ArrayList<Product>

) : Parcelable