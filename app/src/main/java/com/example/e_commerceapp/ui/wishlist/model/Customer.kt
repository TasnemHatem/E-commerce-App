package com.example.e_commerceapp.ui.wishlist.model


import com.google.gson.annotations.SerializedName

data class Customer(
    @SerializedName("id")
    val customerId: Long,
    @SerializedName("email")
    val customerEmail: String,
    @SerializedName("first_name")
    val firstName: String,
    @SerializedName("last_name")
    val lastName: String,
)