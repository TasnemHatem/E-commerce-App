package com.example.e_commerceapp.ui.wishlist.model


import com.google.gson.annotations.SerializedName

data class LineItem(
    @SerializedName("id")
    val id: Long,
    @SerializedName("price")
    val productPrice: String,
    @SerializedName("title")
    val productImg: String,
    @SerializedName("quantity")
    val quantity: Int,
)