package com.example.e_commerceapp.ui.wishlist.model


import android.util.Log
import com.example.e_commerceapp.ui.cart.model.LineItemsItem
import com.example.e_commerceapp.ui.category.model.Product
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class LineItem(
    @SerializedName("id")
    val id: Long,
    @SerializedName("price")
    val price: String,
    @SerializedName("properties")
    val properties: List<Property>,
    @SerializedName("quantity")
    val quantity: Int,
    @SerializedName("title")
    val title: String,
    @field:SerializedName("variant_id")
    @Expose val variantId: Long? = null
    )

fun LineItem.toListItem(): LineItemsItem {
    return LineItemsItem(
        quantity = 1,
        title = title,
        variantId = variantId,
        price = price,
        properties = listOf(Property("0",properties[1].value)),
    )
}

