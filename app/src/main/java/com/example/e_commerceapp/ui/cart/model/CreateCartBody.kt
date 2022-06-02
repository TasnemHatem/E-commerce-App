package com.example.e_commerceapp.ui.cart.model

import com.google.gson.annotations.SerializedName

data class CreateCartBody(

    @field:SerializedName("draft_order")
    val draftOrder: DraftOrder? = null,
)

data class DraftOrder(

    @field:SerializedName("line_items")
    val lineItems: List<LineItemsItem?>? = null,

    @field:SerializedName("email")
    val email: String? = null,

    @field:SerializedName("customer")
    val customer: Customer? = null,
)
