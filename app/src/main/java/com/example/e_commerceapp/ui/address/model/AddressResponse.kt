package com.example.e_commerceapp.ui.address.model


import com.google.gson.annotations.SerializedName

data class AddressResponse(
    @SerializedName("addresses")
    val addresses: List<Address>
)