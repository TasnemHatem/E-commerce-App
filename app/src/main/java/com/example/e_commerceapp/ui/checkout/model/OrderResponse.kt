package com.example.e_commerceapp.ui.checkout.model

import com.example.e_commerceapp.ui.order.model.Order
import com.google.gson.annotations.SerializedName

data class OrderResponse(
	@field:SerializedName("order")
	val order: Order? = null
)