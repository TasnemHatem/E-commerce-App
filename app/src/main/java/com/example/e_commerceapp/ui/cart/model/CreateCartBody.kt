package com.example.e_commerceapp.ui.cart.model

import com.google.gson.annotations.SerializedName

data class CreateCartBody(

    @field:SerializedName("draft_order")
    val draftOrder: DraftOrder? = null,
)
