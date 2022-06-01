package com.example.e_commerceapp.ui.wishlist.model


import com.google.gson.annotations.SerializedName

data class LineItem(
//    @SerializedName("id")
//    val id: Int,
    @SerializedName("product_id")
    val productId: Int,
    @SerializedName("price")
    val price: String,
    @SerializedName("title")
    val title: String,
    @SerializedName("quantity")
    val quantity: Int,
)