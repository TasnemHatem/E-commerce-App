package com.example.e_commerceapp.ui.wishlist.hbl


import com.google.gson.annotations.SerializedName

data class TaxLine(
    @SerializedName("price")
    val price: String,
    @SerializedName("rate")
    val rate: Double,
    @SerializedName("title")
    val title: String
)