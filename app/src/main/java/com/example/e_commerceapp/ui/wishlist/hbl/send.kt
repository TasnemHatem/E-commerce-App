package com.example.e_commerceapp.ui.wishlist.hbl


import com.google.gson.annotations.SerializedName

data class send(
    @SerializedName("draft_order")
    val draftOrder: DraftOrder
)