package com.example.e_commerceapp.ui.cart.model

import com.google.gson.annotations.SerializedName

data class CreateCartResponse(

	@field:SerializedName("draft_order")
	val draftOrder: DraftOrder? = null
)

data class TotalShippingPriceSet(

	@field:SerializedName("shop_money")
	val shopMoney: ShopMoney? = null,

	@field:SerializedName("presentment_money")
	val presentmentMoney: PresentmentMoney? = null
)

data class SubtotalPriceSet(

	@field:SerializedName("shop_money")
	val shopMoney: ShopMoney? = null,

	@field:SerializedName("presentment_money")
	val presentmentMoney: PresentmentMoney? = null
)

data class TotalPriceSet(

	@field:SerializedName("shop_money")
	val shopMoney: ShopMoney? = null,

	@field:SerializedName("presentment_money")
	val presentmentMoney: PresentmentMoney? = null
)


data class PresentmentMoney(

	@field:SerializedName("amount")
	val amount: String? = null,

	@field:SerializedName("currency_code")
	val currencyCode: String? = null
)

data class TotalDiscountsSet(

	@field:SerializedName("shop_money")
	val shopMoney: ShopMoney? = null,

	@field:SerializedName("presentment_money")
	val presentmentMoney: PresentmentMoney? = null
)

data class TotalLineItemsPriceSet(

	@field:SerializedName("shop_money")
	val shopMoney: ShopMoney? = null,

	@field:SerializedName("presentment_money")
	val presentmentMoney: PresentmentMoney? = null
)

data class TaxLinesItem(

	@field:SerializedName("rate")
	val rate: Double? = null,

	@field:SerializedName("price")
	val price: String? = null,

	@field:SerializedName("title")
	val title: String? = null
)

data class ShopMoney(

	@field:SerializedName("amount")
	val amount: String? = null,

	@field:SerializedName("currency_code")
	val currencyCode: String? = null
)


data class TotalTaxSet(

	@field:SerializedName("shop_money")
	val shopMoney: ShopMoney? = null,

	@field:SerializedName("presentment_money")
	val presentmentMoney: PresentmentMoney? = null
)
