package com.example.e_commerceapp.ui.home.model


import com.google.gson.annotations.SerializedName

data class VendorsResponse(
    @SerializedName("smart_collections")
    var smartCollections: List<SmartCollection>
)