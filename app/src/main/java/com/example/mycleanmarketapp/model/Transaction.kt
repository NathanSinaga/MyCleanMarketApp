package com.example.mycleanmarketapp.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Transaction @JvmOverloads constructor(
    var TransId: String = "",
    var TransDate: String = "",
    var TransTotal: Int = 0

) : Parcelable