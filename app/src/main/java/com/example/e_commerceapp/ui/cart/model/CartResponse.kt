package com.example.e_commerceapp.ui.cart.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class CartResponse(
	@field:SerializedName("draft_order")
	@Expose
	var draftOrder: DraftOrder? = null
)