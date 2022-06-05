package com.example.e_commerceapp.ui.wishlist.hbl


import com.google.gson.annotations.SerializedName

data class LineItem(
    @SerializedName("admin_graphql_api_id")
    val adminGraphqlApiId: String,
    @SerializedName("applied_discount")
    val appliedDiscount: Any,
    @SerializedName("custom")
    val custom: Boolean,
    @SerializedName("fulfillment_service")
    val fulfillmentService: String,
    @SerializedName("gift_card")
    val giftCard: Boolean,
    @SerializedName("grams")
    val grams: Int,
    @SerializedName("id")
    val id: Long,
    @SerializedName("name")
    val name: String,
    @SerializedName("price")
    val price: String,
    @SerializedName("product_id")
    val productId: Any,
    @SerializedName("properties")
    val properties: List<Property>,
    @SerializedName("quantity")
    val quantity: Int,
    @SerializedName("requires_shipping")
    val requiresShipping: Boolean,
    @SerializedName("sku")
    val sku: Any,
    @SerializedName("tax_lines")
    val taxLines: List<TaxLine>,
    @SerializedName("taxable")
    val taxable: Boolean,
    @SerializedName("title")
    val title: String,
    @SerializedName("variant_id")
    val variantId: Any,
    @SerializedName("variant_title")
    val variantTitle: Any,
    @SerializedName("vendor")
    val vendor: Any
)