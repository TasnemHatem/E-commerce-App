package com.example.e_commerceapp.ui.category.model


import com.google.gson.annotations.SerializedName

data class Option(
    var id: Long,
    var name: String,
    var position: Int,
    @SerializedName("product_id")
    var productId: Long,
    var values: List<String>
)