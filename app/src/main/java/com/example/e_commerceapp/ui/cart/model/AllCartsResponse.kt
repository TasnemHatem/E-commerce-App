package com.example.e_commerceapp.ui.cart.model

import android.os.Parcelable
import com.example.e_commerceapp.ui.category.model.Product
import com.example.e_commerceapp.ui.wishlist.model.Property
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

data class AllCartsResponse(

	@field:SerializedName("draft_orders")
	@Expose val draftOrders: List<DraftOrder?>? = null
)
@Parcelize
data class LineItemsItem(

	@SerializedName("properties")
	var properties: List<Property>? =null,
	@field:SerializedName("fulfillment_status")
	@Expose val fulfillmentStatus: String? = null,

	@field:SerializedName("quantity")
	var quantity: Int? = null,

	@field:SerializedName("taxable")
	@Expose val taxable: Boolean? = null,

	@field:SerializedName("gift_card")
	@Expose val giftCard: Boolean? = null,

	@field:SerializedName("fulfillment_service")
	@Expose val fulfillmentService: String? = null,

	@field:SerializedName("applied_discount")
	@Expose val appliedDiscount: AppliedDiscount? = null,

	@field:SerializedName("requires_shipping")
	@Expose val requiresShipping: Boolean? = null,

	@field:SerializedName("custom")
	@Expose val custom: Boolean? = null,

	@field:SerializedName("title")
	@Expose val title: String? = null,

	@field:SerializedName("variant_id")
	@Expose val variantId: Long? = null,

	@field:SerializedName("price")
	@Expose val price: String? = null,

	@field:SerializedName("product_id")
	@Expose val productId: Long? = null,

	@field:SerializedName("admin_graphql_api_id")
	@Expose val adminGraphqlApiId: String? = null,

	@field:SerializedName("name")
	@Expose val name: String? = null,

	@field:SerializedName("id")
	@Expose val id: Long? = null,

	@field:SerializedName("sku")
	@Expose val sku: String? = null,

	@field:SerializedName("grams")
	@Expose val grams: Int? = null,
	@field:SerializedName("product")
	@Expose val product: Product? = null,


	):Parcelable

data class ShippingAddress(

	@field:SerializedName("zip")
	@Expose val zip: String? = null,

	@field:SerializedName("country")
	@Expose val country: String? = null,

	@field:SerializedName("city")
	@Expose val city: String? = null,

	@field:SerializedName("address2")
	@Expose val address2: String? = null,

	@field:SerializedName("address1")
	@Expose val address1: String? = null,

	@field:SerializedName("latitude")
	@Expose val latitude: Double? = null,

	@field:SerializedName("last_name")
	@Expose val lastName: String? = null,

	@field:SerializedName("province_code")
	@Expose val provinceCode: String? = null,

	@field:SerializedName("country_code")
	@Expose val countryCode: String? = null,

	@field:SerializedName("province")
	@Expose val province: String? = null,

	@field:SerializedName("phone")
	@Expose val phone: String? = null,

	@field:SerializedName("name")
	@Expose val name: String? = null,

	@field:SerializedName("company")
	@Expose val company: Any? = null,

	@field:SerializedName("first_name")
	@Expose val firstName: String? = null,

	@field:SerializedName("longitude")
	@Expose val longitude: Double? = null
)

@Parcelize
data class AppliedDiscount(

	@field:SerializedName("amount")
	@Expose val amount: String? = null,

	@field:SerializedName("value_type")
	@Expose val valueType: String? = null,

	@field:SerializedName("description")
	@Expose val description: String? = null,

	@field:SerializedName("title")
	@Expose val title: String? = null,

	@field:SerializedName("value")
	@Expose val value: String? = null
):Parcelable

@Parcelize
data class Customer(

	@field:SerializedName("total_spent")
	@Expose val totalSpent: String? = null,

	@field:SerializedName("note")
	@Expose val note: String? = null,

	@field:SerializedName("last_order_name")
	@Expose val lastOrderName: String? = null,

	@field:SerializedName("last_order_id")
	@Expose val lastOrderId: Long? = null,

	@field:SerializedName("tax_exempt")
	@Expose val taxExempt: Boolean? = null,

	@field:SerializedName("created_at")
	@Expose val createdAt: String? = null,

	@field:SerializedName("last_name")
	@Expose val lastName: String? = null,

//	@field:SerializedName("multipass_identifier")
//	@Expose val multipassIdentifier: Any? = null,

	@field:SerializedName("verified_email")
	@Expose val verifiedEmail: Boolean? = null,

	@field:SerializedName("tags")
	@Expose val tags: String? = null,

	@field:SerializedName("accepts_marketing_updated_at")
	@Expose val acceptsMarketingUpdatedAt: String? = null,

	@field:SerializedName("orders_count")
	@Expose val ordersCount: Int? = null,

	@field:SerializedName("default_address")
	@Expose val defaultAddress: DefaultAddress? = null,

	@field:SerializedName("updated_at")
	@Expose val updatedAt: String? = null,

	@field:SerializedName("accepts_marketing")
	@Expose val acceptsMarketing: Boolean? = null,

	@field:SerializedName("phone")
	@Expose val phone: String? = null,

	@field:SerializedName("admin_graphql_api_id")
	@Expose val adminGraphqlApiId: String? = null,

//	@field:SerializedName("tax_exemptions")
//	@Expose val taxExemptions: List<Any?>? = null,

	@field:SerializedName("currency")
	@Expose val currency: String? = null,

	@field:SerializedName("id")
	@Expose val id: Long? = null,

	@field:SerializedName("state")
	@Expose val state: String? = null,

//	@field:SerializedName("marketing_opt_in_level")
//	@Expose val marketingOptInLevel: Any? = null,

	@field:SerializedName("first_name")
	@Expose val firstName: String? = null,

	@field:SerializedName("email")
	@Expose val email: String? = null
):Parcelable

@Parcelize
data class DefaultAddress(

	@field:SerializedName("zip")
	@Expose val zip: String? = null,

	@field:SerializedName("country")
	@Expose val country: String? = null,

	@field:SerializedName("address2")
	@Expose val address2: String? = null,

	@field:SerializedName("city")
	@Expose val city: String? = null,

	@field:SerializedName("address1")
	@Expose val address1: String? = null,

	@field:SerializedName("last_name")
	@Expose val lastName: String? = null,

	@field:SerializedName("province_code")
	@Expose val provinceCode: String? = null,

	@field:SerializedName("country_code")
	@Expose val countryCode: String? = null,

	@field:SerializedName("default")
	@Expose val jsonMemberDefault: Boolean? = null,

	@field:SerializedName("province")
	@Expose val province: String? = null,

	@field:SerializedName("phone")
	@Expose val phone: String? = null,

	@field:SerializedName("name")
	@Expose val name: String? = null,

	@field:SerializedName("country_name")
	@Expose val countryName: String? = null,

//	@field:SerializedName("company")
//	@Expose val company: Any? = null,

	@field:SerializedName("id")
	@Expose val id: Long? = null,

	@field:SerializedName("customer_id")
	@Expose val customerId: Int? = null,

	@field:SerializedName("first_name")
	@Expose val firstName: String? = null
):Parcelable

data class BillingAddress(

	@field:SerializedName("zip")
	@Expose val zip: String? = null,

	@field:SerializedName("country")
	@Expose val country: String? = null,

	@field:SerializedName("city")
	@Expose val city: String? = null,

	@field:SerializedName("address2")
	@Expose val address2: String? = null,

	@field:SerializedName("address1")
	@Expose val address1: String? = null,

	@field:SerializedName("latitude")
	@Expose val latitude: Double? = null,

	@field:SerializedName("last_name")
	@Expose val lastName: String? = null,

	@field:SerializedName("province_code")
	@Expose val provinceCode: String? = null,

	@field:SerializedName("country_code")
	@Expose val countryCode: String? = null,

	@field:SerializedName("province")
	@Expose val province: String? = null,

	@field:SerializedName("phone")
	@Expose val phone: String? = null,

	@field:SerializedName("name")
	@Expose val name: String? = null,

	@field:SerializedName("company")
	@Expose val company: Any? = null,

	@field:SerializedName("first_name")
	@Expose val firstName: String? = null,

	@field:SerializedName("longitude")
	@Expose val longitude: Double? = null
)

data class ShippingLine(

	@field:SerializedName("price")
	@Expose val price: String? = null,

	@field:SerializedName("custom")
	@Expose val custom: Boolean? = null,

	@field:SerializedName("handle")
	@Expose val handle: Any? = null,

	@field:SerializedName("title")
	@Expose val title: String? = null
)


