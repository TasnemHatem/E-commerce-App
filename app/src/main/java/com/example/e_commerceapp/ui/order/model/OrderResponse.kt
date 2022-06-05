package com.example.e_commerceapp.ui.order.model


import com.google.gson.annotations.SerializedName

data class OrderResponse(
    @SerializedName("orders")
    val orders: List<Order>
)