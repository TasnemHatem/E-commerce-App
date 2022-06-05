package com.example.e_commerceapp.ui.product.network

import com.example.e_commerceapp.ui.category.model.ProductsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ProductService {
    @GET("products.json")
    suspend fun getVendorProducts(@Query("vendor") vendor:String): Response<ProductsResponse>
}