package com.example.e_commerceapp.ui.auth.model

import com.example.e_commerceapp.ui.address.model.Address
import com.google.gson.annotations.SerializedName

data class Customer(
    @SerializedName("id")
    val customerId: Long? = null,

    @SerializedName("email")
    val email: String?,

    @SerializedName("first_name")
    val firstName: String? = "",

    @SerializedName("last_name")
    val lastName: String? = "",

    @SerializedName("addresses")
    val addresses: List<Address>? = listOf(),

    @SerializedName("currency")
    val currency: String? = "EGP",

    @SerializedName("tags")
    val password: String? = "",

    @SerializedName("verified_email")
    val verified_email: Boolean? = true,

    @SerializedName("multipass_identifier")
    var cartId: String? = "",

    @SerializedName("note")
    var favouriteId: String? = "",

    ) {

    fun getDefaultOrFirst(): Address? {
        return if (!addresses.isNullOrEmpty()) {
            return addresses.firstOrNull {
                it.default
            } ?: addresses.first()
        }else{
            null
        }
    }

}
