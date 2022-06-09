package com.example.e_commerceapp.ui.newaddress.model


import com.google.gson.annotations.SerializedName

data class ErrorPojo(
    @SerializedName("errors")
    val errors: Errors
)