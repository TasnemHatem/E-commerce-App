package com.example.e_commerceapp.ui.wishlist.model


import com.google.gson.annotations.SerializedName

data class LineItem(
    @SerializedName("id")
    val id: Long,
    @SerializedName("price")
    val price: String,
    @SerializedName("properties")
    val properties: List<Property>,
    @SerializedName("quantity")
    val quantity: Int,
    @SerializedName("title")
    val title: String
)