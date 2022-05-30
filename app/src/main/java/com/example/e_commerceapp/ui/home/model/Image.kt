package com.example.e_commerceapp.ui.home.model


import com.google.gson.annotations.SerializedName

data class Image(
    var alt: Any,
    @SerializedName("created_at")
    var createdAt: String,
    var height: Int,
    var src: String,
    var width: Int
)