package com.example.e_commerceapp.ui.wishlist.model


import com.google.gson.annotations.SerializedName

data class DraftOrder(
    @SerializedName("id")
    val id: Long,
    @SerializedName("note")
    val isWishlist: Boolean,
    @SerializedName("customer")
    val customer: Customer,
    @SerializedName("line_items")
    val lineItems: List<LineItem>,
    @SerializedName("tags")
    val productId: String
)