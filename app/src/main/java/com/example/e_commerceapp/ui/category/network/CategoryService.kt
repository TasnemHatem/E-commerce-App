package com.example.e_commerceapp.ui.category.network

import com.example.e_commerceapp.ui.category.model.ProductsResponse
import com.example.e_commerceapp.ui.home.model.VendorsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface CategoryService {

    @GET("products.json")
    suspend fun getProducts(@Query("product_type") productType:String): Response<ProductsResponse>
}