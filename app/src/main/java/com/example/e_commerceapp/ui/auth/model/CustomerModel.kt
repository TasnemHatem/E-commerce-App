package com.example.e_commerceapp.ui.auth.model

import com.google.gson.annotations.SerializedName

data class CustomerModel (
    @SerializedName( "customer")
    val customer: Customer?,

    @SerializedName( "errors")
    val error: Error? = null,
)
