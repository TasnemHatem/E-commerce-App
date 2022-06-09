package com.example.e_commerceapp.ui.checkout.model

import android.os.Parcelable
import com.example.e_commerceapp.ui.address.model.Address
import com.example.e_commerceapp.ui.cart.model.DiscountCode
import com.example.e_commerceapp.ui.cart.model.LineItemsItem
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
    var shippingAddress: Address? = null,

    @field:SerializedName("line_items")
    var lineItems: List<LineItemsItem?>? = null,

    @field:SerializedName("discount_codes")
    var discountCodes: List<DiscountCode?>? = null,
) : Parcelable
