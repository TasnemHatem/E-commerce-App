package com.example.e_commerceapp.ui.wishlist.model


import com.google.gson.annotations.SerializedName

data class Customer(
    @SerializedName("email")
    val customerEmail: String,
    @SerializedName("id")
    val customerId: Long,
)