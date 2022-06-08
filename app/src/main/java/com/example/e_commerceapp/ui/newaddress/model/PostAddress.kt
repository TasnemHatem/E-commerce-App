package com.example.e_commerceapp.ui.newaddress.model


import com.example.e_commerceapp.ui.address.model.Address
import com.google.gson.annotations.SerializedName

data class PostAddress(
    @SerializedName("address")
    val address: Address
)