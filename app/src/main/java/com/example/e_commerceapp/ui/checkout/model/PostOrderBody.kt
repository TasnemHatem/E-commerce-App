package com.example.e_commerceapp.ui.checkout.model

import android.os.Parcelable
import com.example.e_commerceapp.ui.cart.model.DiscountCode
import com.example.e_commerceapp.ui.cart.model.LineItemsItem
import com.example.e_commerceapp.ui.order.model.ShippingAddress
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class PostOrderBody(

    @field:SerializedName("order")
    val order: Order? = null,
) : Parcelable


@Parcelize
data class Order(
    @field:SerializedName("shipping_address")
    val shippingAddress: ShippingAddress? = null,

    @field:SerializedName("line_items")
    val lineItems: List<LineItemsItem?>? = null,

    @field:SerializedName("discount_codes")
    val discountCodes: List<DiscountCode?>? = null,
) : Parcelable
