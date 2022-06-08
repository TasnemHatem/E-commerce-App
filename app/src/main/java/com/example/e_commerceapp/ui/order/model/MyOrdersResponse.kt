package com.example.e_commerceapp.ui.order.model


import com.google.gson.annotations.SerializedName

data class MyOrdersResponse(
    @SerializedName("orders")
    val orders: List<Order>
)