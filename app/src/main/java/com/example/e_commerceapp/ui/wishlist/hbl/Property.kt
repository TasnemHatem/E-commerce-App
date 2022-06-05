package com.example.e_commerceapp.ui.wishlist.hbl


import com.google.gson.annotations.SerializedName

data class Property(
    @SerializedName("name")
    val name: String,
    @SerializedName("value")
    val value: String
)