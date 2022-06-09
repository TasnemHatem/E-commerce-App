package com.example.e_commerceapp.ui.address.model


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Address(
    @SerializedName("address1")//+address2
    val address1: String,
    @SerializedName("address2")//
    val address2: String,
    @SerializedName("city")//+country
    val city: String,
    @SerializedName("country")//
    val country: String,
    @SerializedName("default")
    val default: Boolean,
    @SerializedName("first_name")//+last name
    val firstName: String,
    @SerializedName("id")
    val id: Long?,
    @SerializedName("customer_id")
    val userId: Long?,
    @SerializedName("last_name")//
    val lastName: String,
    @SerializedName("phone")//
    val phone: String,
):Parcelable