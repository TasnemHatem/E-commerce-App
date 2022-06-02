package com.example.e_commerceapp.ui.address.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Address(

    @SerializedName("id")
    val id: Long? = 0,

    @SerializedName("address1")
    val address1: String? = "",

    @SerializedName("address2")
    val address: String? = "",

    @SerializedName("city")
    val city: String? = "",

    @SerializedName("country")
    val country: String? = "",


    @SerializedName("company")
    val latLng: String? = "",

    @SerializedName("first_name")
    val firstName: String? = "",

    @SerializedName("last_name")
    val lastName: String? = "",

    @SerializedName("zip")
    val zip: String? = "",

    @SerializedName("default")
    val default: Boolean = false,

    @SerializedName("phone")
    val phone: String? = "",

    ) : Parcelable