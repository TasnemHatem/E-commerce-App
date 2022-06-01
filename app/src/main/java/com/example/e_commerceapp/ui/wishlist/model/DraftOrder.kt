package com.example.e_commerceapp.ui.wishlist.model


import com.google.gson.annotations.SerializedName

data class DraftOrder(
    @SerializedName("id")
    val id: Long,
    @SerializedName("customer")
    val customer: Customer,
    @SerializedName("line_items")
    val lineItems: List<LineItem>,

//    @SerializedName("order_id")
//    val orderId: Int,
)