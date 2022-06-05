package com.example.e_commerceapp.ui.cart.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class CreateCartBody(

    @field:SerializedName("draft_order")
    @Expose
    val draftOrder: DraftOrder? = null,
)
