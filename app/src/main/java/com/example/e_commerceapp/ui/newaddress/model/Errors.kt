package com.example.e_commerceapp.ui.newaddress.model


import com.google.gson.annotations.SerializedName

data class Errors(
    @SerializedName("country")
    val country: List<String>,
    @SerializedName("signature")
    val signature: List<String>
)