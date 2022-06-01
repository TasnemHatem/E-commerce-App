package com.example.e_commerceapp.ui.auth.model

import com.google.gson.annotations.SerializedName

data class CustomerLoginModel(
    @SerializedName( "customers")
    val customer: List<Customer?>,

    @SerializedName( "error")
    val error: Error? = null,
)