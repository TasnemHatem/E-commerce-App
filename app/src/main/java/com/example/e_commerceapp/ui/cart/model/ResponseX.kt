package com.example.e_commerceapp.ui.cart.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName




data class DraftOrder(
	@field:SerializedName("note")
	@Expose val note: String? = null,

	@field:SerializedName("applied_discount")
	@Expose val appliedDiscount: AppliedDiscount? = null,

	@field:SerializedName("created_at")
	@Expose val createdAt: String? = null,

	@field:SerializedName("billing_address")
	@Expose val billingAddress: BillingAddress? = null,

	@field:SerializedName("taxes_included")
	@Expose val taxesIncluded: Boolean? = null,

	@field:SerializedName("line_items")
	@Expose val lineItems: MutableList<LineItemsItem?>? = null,

	@field:SerializedName("payment_terms")
	@Expose val paymentTerms: Any? = null,

	@field:SerializedName("updated_at")
	@Expose val updatedAt: String? = null,

	@field:SerializedName("tax_lines")
	@Expose val taxLines: List<TaxLinesItem?>? = null,

	@field:SerializedName("currency")
	@Expose val currency: String? = null,

	@field:SerializedName("id")
	@Expose val id: Long? = null,

	@field:SerializedName("shipping_address")
	@Expose val shippingAddress: ShippingAddress? = null,

	@field:SerializedName("email")
	@Expose val email: String? = null,

	@field:SerializedName("subtotal_price")
	@Expose val subtotalPrice: String? = null,

	@field:SerializedName("total_price")
	@Expose val totalPrice: String? = null,

	@field:SerializedName("tax_exempt")
	@Expose val taxExempt: Boolean? = null,

	@field:SerializedName("invoice_sent_at")
	@Expose val invoiceSentAt: Any? = null,

	@field:SerializedName("total_tax")
	@Expose val totalTax: String? = null,

	@field:SerializedName("tags")
	@Expose val tags: String? = null,

	@field:SerializedName("completed_at")
	@Expose val completedAt: Any? = null,

	@field:SerializedName("note_attributes")
	@Expose val noteAttributes: List<Any?>? = null,

	@field:SerializedName("admin_graphql_api_id")
	@Expose val adminGraphqlApiId: String? = null,

	@field:SerializedName("name")
	@Expose val name: String? = null,

	@field:SerializedName("shipping_line")
	@Expose val shippingLine: ShippingLine? = null,

	@field:SerializedName("order_id")
	@Expose val orderId: Any? = null,

	@field:SerializedName("invoice_url")
	@Expose val invoiceUrl: String? = null,

	@field:SerializedName("status")
	@Expose val status: String? = null,

	@field:SerializedName("customer")
	@Expose val customer: Customer? = null
)




