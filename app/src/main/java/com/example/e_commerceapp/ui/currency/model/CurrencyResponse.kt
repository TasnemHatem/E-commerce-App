package com.example.e_commerceapp.ui.currency.model


import com.google.gson.annotations.SerializedName

data class CurrencyResponse(
    @SerializedName("base_code")
    val baseCode: String?,
    @SerializedName("conversion_rates")
    val conversionRates: ConversionRates?,
)