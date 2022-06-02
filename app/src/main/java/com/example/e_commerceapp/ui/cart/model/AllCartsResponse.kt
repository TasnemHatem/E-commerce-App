package com.example.e_commerceapp.ui.cart.model

import com.google.gson.annotations.SerializedName

data class AllCartsResponse(

	@field:SerializedName("draft_orders")
	val draftOrders: List<DraftOrdersItem?>? = null
)

data class LineItemsItem(

	@field:SerializedName("variant_title")
	val variantTitle: String? = null,

	@field:SerializedName("quantity")
	val quantity: Int? = null,

	@field:SerializedName("taxable")
	val taxable: Boolean? = null,

	@field:SerializedName("gift_card")
	val giftCard: Boolean? = null,

	@field:SerializedName("fulfillment_service")
	val fulfillmentService: String? = null,

	@field:SerializedName("applied_discount")
	val appliedDiscount: Any? = null,

	@field:SerializedName("requires_shipping")
	val requiresShipping: Boolean? = null,

	@field:SerializedName("custom")
	val custom: Boolean? = null,

	@field:SerializedName("title")
	val title: String? = null,

	@field:SerializedName("variant_id")
	val variantId: Int? = null,

	@field:SerializedName("tax_lines")
	val taxLines: List<Any?>? = null,

	@field:SerializedName("vendor")
	val vendor: Any? = null,

	@field:SerializedName("price")
	val price: String? = null,

	@field:SerializedName("product_id")
	val productId: Int? = null,

	@field:SerializedName("admin_graphql_api_id")
	val adminGraphqlApiId: String? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("sku")
	val sku: String? = null,

	@field:SerializedName("grams")
	val grams: Int? = null,

	@field:SerializedName("properties")
	val properties: List<Any?>? = null
)

data class ShippingAddress(

	@field:SerializedName("zip")
	val zip: String? = null,

	@field:SerializedName("country")
	val country: String? = null,

	@field:SerializedName("city")
	val city: String? = null,

	@field:SerializedName("address2")
	val address2: String? = null,

	@field:SerializedName("address1")
	val address1: String? = null,

	@field:SerializedName("latitude")
	val latitude: Double? = null,

	@field:SerializedName("last_name")
	val lastName: String? = null,

	@field:SerializedName("province_code")
	val provinceCode: String? = null,

	@field:SerializedName("country_code")
	val countryCode: String? = null,

	@field:SerializedName("province")
	val province: String? = null,

	@field:SerializedName("phone")
	val phone: String? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("company")
	val company: Any? = null,

	@field:SerializedName("first_name")
	val firstName: String? = null,

	@field:SerializedName("longitude")
	val longitude: Double? = null
)

data class AppliedDiscount(

	@field:SerializedName("amount")
	val amount: String? = null,

	@field:SerializedName("value_type")
	val valueType: String? = null,

	@field:SerializedName("description")
	val description: String? = null,

	@field:SerializedName("title")
	val title: Any? = null,

	@field:SerializedName("value")
	val value: String? = null
)

data class DraftOrdersItem(

	@field:SerializedName("note")
	val note: String? = null,

	@field:SerializedName("applied_discount")
	val appliedDiscount: Any? = null,

	@field:SerializedName("created_at")
	val createdAt: String? = null,

	@field:SerializedName("billing_address")
	val billingAddress: BillingAddress? = null,

	@field:SerializedName("taxes_included")
	val taxesIncluded: Boolean? = null,

	@field:SerializedName("line_items")
	val lineItems: List<LineItemsItem?>? = null,

	@field:SerializedName("updated_at")
	val updatedAt: String? = null,

	@field:SerializedName("tax_lines")
	val taxLines: List<Any?>? = null,

	@field:SerializedName("currency")
	val currency: String? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("shipping_address")
	val shippingAddress: ShippingAddress? = null,

	@field:SerializedName("email")
	val email: String? = null,

	@field:SerializedName("subtotal_price")
	val subtotalPrice: String? = null,

	@field:SerializedName("total_price")
	val totalPrice: String? = null,

	@field:SerializedName("tax_exempt")
	val taxExempt: Boolean? = null,

	@field:SerializedName("invoice_sent_at")
	val invoiceSentAt: Any? = null,

	@field:SerializedName("total_tax")
	val totalTax: String? = null,

	@field:SerializedName("tags")
	val tags: String? = null,

	@field:SerializedName("completed_at")
	val completedAt: Any? = null,

	@field:SerializedName("note_attributes")
	val noteAttributes: List<Any?>? = null,

	@field:SerializedName("admin_graphql_api_id")
	val adminGraphqlApiId: String? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("shipping_line")
	val shippingLine: Any? = null,

	@field:SerializedName("order_id")
	val orderId: Int? = null,

	@field:SerializedName("invoice_url")
	val invoiceUrl: String? = null,

	@field:SerializedName("status")
	val status: String? = null,

	@field:SerializedName("customer")
	val customer: Customer? = null
)

data class Customer(

	@field:SerializedName("total_spent")
	val totalSpent: String? = null,

	@field:SerializedName("note")
	val note: Any? = null,

	@field:SerializedName("last_order_name")
	val lastOrderName: String? = null,

	@field:SerializedName("last_order_id")
	val lastOrderId: Int? = null,

	@field:SerializedName("tax_exempt")
	val taxExempt: Boolean? = null,

	@field:SerializedName("created_at")
	val createdAt: String? = null,

	@field:SerializedName("last_name")
	val lastName: String? = null,

	@field:SerializedName("multipass_identifier")
	val multipassIdentifier: Any? = null,

	@field:SerializedName("verified_email")
	val verifiedEmail: Boolean? = null,

	@field:SerializedName("tags")
	val tags: String? = null,

	@field:SerializedName("accepts_marketing_updated_at")
	val acceptsMarketingUpdatedAt: String? = null,

	@field:SerializedName("orders_count")
	val ordersCount: Int? = null,

	@field:SerializedName("default_address")
	val defaultAddress: DefaultAddress? = null,

	@field:SerializedName("updated_at")
	val updatedAt: String? = null,

	@field:SerializedName("accepts_marketing")
	val acceptsMarketing: Boolean? = null,

	@field:SerializedName("phone")
	val phone: String? = null,

	@field:SerializedName("admin_graphql_api_id")
	val adminGraphqlApiId: String? = null,

	@field:SerializedName("tax_exemptions")
	val taxExemptions: List<Any?>? = null,

	@field:SerializedName("currency")
	val currency: String? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("state")
	val state: String? = null,

	@field:SerializedName("marketing_opt_in_level")
	val marketingOptInLevel: Any? = null,

	@field:SerializedName("first_name")
	val firstName: String? = null,

	@field:SerializedName("email")
	val email: String? = null
)

data class DefaultAddress(

	@field:SerializedName("zip")
	val zip: String? = null,

	@field:SerializedName("country")
	val country: String? = null,

	@field:SerializedName("address2")
	val address2: String? = null,

	@field:SerializedName("city")
	val city: String? = null,

	@field:SerializedName("address1")
	val address1: String? = null,

	@field:SerializedName("last_name")
	val lastName: Any? = null,

	@field:SerializedName("province_code")
	val provinceCode: String? = null,

	@field:SerializedName("country_code")
	val countryCode: String? = null,

	@field:SerializedName("default")
	val jsonMemberDefault: Boolean? = null,

	@field:SerializedName("province")
	val province: String? = null,

	@field:SerializedName("phone")
	val phone: String? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("country_name")
	val countryName: String? = null,

	@field:SerializedName("company")
	val company: Any? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("customer_id")
	val customerId: Int? = null,

	@field:SerializedName("first_name")
	val firstName: Any? = null
)

data class BillingAddress(

	@field:SerializedName("zip")
	val zip: String? = null,

	@field:SerializedName("country")
	val country: String? = null,

	@field:SerializedName("city")
	val city: String? = null,

	@field:SerializedName("address2")
	val address2: String? = null,

	@field:SerializedName("address1")
	val address1: String? = null,

	@field:SerializedName("latitude")
	val latitude: Double? = null,

	@field:SerializedName("last_name")
	val lastName: String? = null,

	@field:SerializedName("province_code")
	val provinceCode: String? = null,

	@field:SerializedName("country_code")
	val countryCode: String? = null,

	@field:SerializedName("province")
	val province: String? = null,

	@field:SerializedName("phone")
	val phone: String? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("company")
	val company: Any? = null,

	@field:SerializedName("first_name")
	val firstName: String? = null,

	@field:SerializedName("longitude")
	val longitude: Double? = null
)

data class ShippingLine(

	@field:SerializedName("price")
	val price: String? = null,

	@field:SerializedName("custom")
	val custom: Boolean? = null,

	@field:SerializedName("handle")
	val handle: Any? = null,

	@field:SerializedName("title")
	val title: String? = null
)
