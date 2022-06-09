package com.example.e_commerceapp.ui.currency.model


import com.google.gson.annotations.SerializedName

data class ConversionRates(
    @SerializedName("USD")
    val uSD: Int,
    @SerializedName("EUR")
    val eUR: Double,
    @SerializedName("GBP")
    val gBP: Double,
    @SerializedName("NOK")
    val nOK: Double,
    @SerializedName("AUD")
    val aUD: Double,
    @SerializedName("CHF")
    val cHF: Double,
    @SerializedName("SEK")
    val sEK: Double,
    @SerializedName("BRL")
    val bRL: Double,
    @SerializedName("PLN")
    val pLN: Double,
    @SerializedName("RUB")
    val rUB: Double,
    @SerializedName("MXN")
    val mXN: Double,
    @SerializedName("DKK")
    val dKK: Double,
    @SerializedName("SAR")
    val sAR: Double,
    @SerializedName("JPY")
    val jPY: Double,
    @SerializedName("HKD")
    val hKD: Double,
    @SerializedName("SGD")
    val sGD: Double,
    @SerializedName("KWD")
    val kWD: Double,
    @SerializedName("AED")
    val aED: Double,
    @SerializedName("QAR")
    val qAR: Double,
    @SerializedName("CAD")
    val cAD: Double,
    @SerializedName("BHD")
    val bHD: Double,
    @SerializedName("NZD")
    val nZD: Double,

    @SerializedName("EGP")
    val eGP: Double,

)