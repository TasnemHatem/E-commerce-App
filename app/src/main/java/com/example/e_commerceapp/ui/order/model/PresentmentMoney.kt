package com.example.e_commerceapp.ui.order.model


import com.google.gson.annotations.SerializedName

data class PresentmentMoney(
    @SerializedName("amount")
    val amount: String,
    @SerializedName("currency_code")
    val currencyCode: String
)