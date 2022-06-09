package com.example.e_commerceapp.ui.wishlist.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Property(
    @SerializedName("name")
    val name: String,
    @SerializedName("value")
    val value: String
):Parcelable
