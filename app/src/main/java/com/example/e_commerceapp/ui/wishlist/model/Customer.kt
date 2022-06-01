package com.example.e_commerceapp.ui.wishlist.model


import com.google.gson.annotations.SerializedName

data class Customer(

    @SerializedName("email")
    val email: String,
    @SerializedName("id")
    val id: Long,

//    @SerializedName("currency")
//    val currency: String,
//    @SerializedName("last_name")
//    val lastName: String,
//    @SerializedName("verified_email")
//    val verifiedEmail: Boolean
)