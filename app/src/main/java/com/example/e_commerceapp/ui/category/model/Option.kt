package com.example.e_commerceapp.ui.category.model


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Option(
    var id: Long,
    var name: String,
    var position: Int,
    @SerializedName("product_id")
    var productId: Long,
    var values: List<String>
):Parcelable