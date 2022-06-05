package com.example.e_commerceapp.ui.category.model


import android.os.Parcelable
import androidx.room.Ignore
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
import java.util.ArrayList

@Parcelize
data class Product(
    @SerializedName("admin_graphql_api_id")
    var adminGraphqlApiId: String,
    @SerializedName("body_html")
    var bodyHtml: String,
    @SerializedName("created_at")
    var createdAt: String,
    var handle: String,
    var id: Long,
    var image: Image,
    var images: List<Image>,
    var options: List<Option>,
    @SerializedName("product_type")
    var productType: String,
    @SerializedName("published_at")
    var publishedAt: String,
    @SerializedName("published_scope")
    var publishedScope: String,
    var status: String,
    var tags: String,
//    @SerializedName("template_suffix")
//    var templateSuffix: String,
    var title: String,
    @SerializedName("updated_at")
    var updatedAt: String,
    var variants: ArrayList<Variant>,
    var vendor: String,
    @Expose(deserialize = false, serialize = false)
    var isFavourite: Boolean = false
) : Parcelable