package com.example.e_commerceapp.ui.cart.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

data class CouponResponse(

	@field:SerializedName("discount_code")
	val discountCode: DiscountCode? = null
)
@Parcelize
data class DiscountCode(

	@field:SerializedName("usage_count")
	val usageCount: Int? = null,

	@field:SerializedName("code")
	val code: String? = null,

	@field:SerializedName("updated_at")
	val updatedAt: String? = null,

	@field:SerializedName("price_rule_id")
	val priceRuleId: Long? = null,

	@field:SerializedName("created_at")
	val createdAt: String? = null,

	@field:SerializedName("id")
	val id: Long? = null,

	@field:SerializedName("amount")
	var amount:String?=null
):Parcelable
