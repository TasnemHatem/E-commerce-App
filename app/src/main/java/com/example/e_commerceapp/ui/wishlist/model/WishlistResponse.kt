package com.example.e_commerceapp.ui.wishlist.model


import com.google.gson.annotations.SerializedName

data class WishlistResponse(
    @SerializedName("draft_orders")
    val draftOrders: List<DraftOrder>
)