package com.example.e_commerceapp.ui.wishlist.hbl


import com.google.gson.annotations.SerializedName

data class DraftOrder(
    @SerializedName("line_items")
    val lineItems: List<LineItem>
)