package com.example.e_commerceapp.ui.category.model


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Image(
    @SerializedName("admin_graphql_api_id")
    var adminGraphqlApiId: String,
  //  var alt: String,
    @SerializedName("created_at")
    var createdAt: String,
    var height: Int,
    var id: Long,
    var position: Int,
    @SerializedName("product_id")
    var productId: Long,
    var src: String,
    @SerializedName("updated_at")
    var updatedAt: String,
    @SerializedName("variant_ids")
    var variantIds: List<Int>,
    var width: Int
):Parcelable