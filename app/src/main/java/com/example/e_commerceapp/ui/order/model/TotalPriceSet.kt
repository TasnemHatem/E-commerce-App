package com.example.e_commerceapp.ui.order.model


import com.google.gson.annotations.SerializedName

data class TotalPriceSet(
    @SerializedName("presentment_money")
    val presentmentMoney: PresentmentMoney,
    @SerializedName("shop_money")
    val shopMoney: ShopMoney
)