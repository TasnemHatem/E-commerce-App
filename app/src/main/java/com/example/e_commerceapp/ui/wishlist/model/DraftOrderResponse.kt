package com.example.e_commerceapp.ui.wishlist.model
import com.google.gson.annotations.SerializedName

data class DraftOrderResponse(
    @SerializedName("draft_order")
    val draftOrder: DraftOrder
)
