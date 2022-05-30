package com.example.e_commerceapp.ui.home.model


import com.google.gson.annotations.SerializedName

data class SmartCollection(
    @SerializedName("admin_graphql_api_id")
    var adminGraphqlApiId: String,
    @SerializedName("body_html")
    var bodyHtml: String,
    var disjunctive: Boolean,
    var handle: String,
    var id: Long,
    var image: Image,
    @SerializedName("published_at")
    var publishedAt: String,
    @SerializedName("published_scope")
    var publishedScope: String,
    var rules: List<Rule>,
    @SerializedName("sort_order")
    var sortOrder: String,
    @SerializedName("template_suffix")
    var templateSuffix: Any,
    var title: String,
    @SerializedName("updated_at")
    var updatedAt: String
)