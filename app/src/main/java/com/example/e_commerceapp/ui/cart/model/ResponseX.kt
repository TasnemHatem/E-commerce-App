package com.example.e_commerceapp.ui.cart.model

import com.google.gson.annotations.SerializedName




data class DraftOrder(

	@field:SerializedName("note")
	val note: String? = null,

	@field:SerializedName("applied_discount")
	val appliedDiscount: AppliedDiscount? = null,

	@field:SerializedName("created_at")
	val createdAt: String? = null,

	@field:SerializedName("billing_address")
	val billingAddress: BillingAddress? = null,

	@field:SerializedName("taxes_included")
	val taxesIncluded: Boolean? = null,

	@field:SerializedName("line_items")
	val lineItems: List<LineItemsItem?>? = null,

	@field:SerializedName("payment_terms")
	val paymentTerms: Any? = null,

	@field:SerializedName("updated_at")
	val updatedAt: String? = null,

	@field:SerializedName("tax_lines")
	val taxLines: List<Any?>? = null,

	@field:SerializedName("currency")
	val currency: String? = null,

	@field:SerializedName("id")
	val id: Long? = null,

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
	val shippingLine: ShippingLine? = null,

	@field:SerializedName("order_id")
	val orderId: Any? = null,

	@field:SerializedName("invoice_url")
	val invoiceUrl: String? = null,

	@field:SerializedName("status")
	val status: String? = null,

	@field:SerializedName("customer")
	val customer: Customer? = null
)




